package es.ciudadescolar.servicios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ciudadescolar.dominio.modelo.Perfil;
import es.ciudadescolar.dominio.modelo.Usuario;
import es.ciudadescolar.persistencia.dao.PerfilDAO;
import es.ciudadescolar.persistencia.dao.UsuarioDAO;
import es.ciudadescolar.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UsuarioService
{
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioService.class);

    public void registrarUsuario(String nombre, String apellidos, LocalDate fecha_nacimiento, String email, String username, String contrasenia) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try
        {
            trans.begin();

            UsuarioDAO userDAO = new UsuarioDAO(em);
            Usuario user = userDAO.buscarPorEmail(email);

            if (user == null) {
                user = new Usuario(nombre, apellidos, fecha_nacimiento, email);
                userDAO.guardar(user);

                PerfilDAO perfDAO = new PerfilDAO(em);
                Perfil perf = new Perfil(username, contrasenia, user);
                perfDAO.guardar(perf);

            } else {
                LOG.warn("Ya existe un usuario con el email " + email + " en la BD.");
            }

            trans.commit();

            LOG.debug("Usuario registrado: " + user.toString());
        }
        catch (RuntimeException e)
        {
            LOG.error("Error durante la transacción: " + e.getMessage());
                
            if (trans != null && trans.isActive()) 
            {
                trans.rollback();
                LOG.debug("Rollback de la transacción.");
            }

            throw e; // Propagamos error al main o a la capa superior
        } finally
        {
            try
            {
                if (em != null && em.isOpen()) {
                    em.close();
                    LOG.debug("EntityManager cerrado.");
                }
            }
            catch (RuntimeException e)
            {
                LOG.error("Error durante el cierre del EtityManager: " + e.getMessage());
                // La transacción ya se ha intentado commit o rollback. 
                // Aquí no propagamos la excepción para evitar ocultar la excepción original que pudo motivar el rollback o fallo de negocio.
            }
        }
    }

    public void eliminarUsuario(Integer idUsuario)
    {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try
        {
            trans.begin();

            UsuarioDAO userDAO = new UsuarioDAO(em);
            Usuario user = userDAO.buscarPorId(idUsuario);

            if (user != null) {
                userDAO.eliminar(user);

                trans.commit();
                LOG.debug("Usuario eliminado de la BD: " + user.toString());
            } else {
                LOG.warn("No hay ningún usurio con este ID.");
            }

        }
        catch (RuntimeException e)
        {
            LOG.error("Error durante la transacción: " + e.getMessage());
                
            if (trans != null && trans.isActive()) 
            {
                trans.rollback();
                LOG.debug("Rollback de la transacción.");
            }

            throw e; // Propagamos error al main o a la capa superior
        } finally
        {
            try
            {
                if (em != null && em.isOpen()) {
                    em.close();
                    LOG.debug("EntityManager cerrado.");
                }
            }
            catch (RuntimeException e)
            {
                LOG.error("Error durante el cierre del EtityManager: " + e.getMessage());
            }
        }
    }

    public void modificarPerfilUsuario(Integer idUsuario, String username)
    {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();

            UsuarioDAO usuarioDAO = new UsuarioDAO(em);
            PerfilDAO perfilDAO = new PerfilDAO(em);

            Usuario userCambiar = usuarioDAO.buscarPorId(idUsuario);

            if (userCambiar != null) {
                Perfil perfCambiar = perfilDAO.buscarPorUsername(username);

                if (perfCambiar != null) {
                    perfCambiar.setUsuario(userCambiar);
                    trans.commit();
                    LOG.debug("El usuario se ha cambiado de perfil correctamente.");
                } else {
                    LOG.warn("El perfil para cambiar de usuario no existe.");
                }
            } else {
                LOG.warn("El usuario introducido por usted no existe.");
            }

        } catch (RuntimeException e) {
            trans.rollback();
            LOG.error("Error durante la transacción: " + e.getMessage());
        }
    }

    public void generarReporte()
    {
        EntityManager em = JPAUtil.getEntityManager();
        BufferedWriter bw = null;

        try {
            UsuarioDAO userDAO = new UsuarioDAO(em);
            List<Usuario> listausUarios = userDAO.recuperarTodosusuarios();

            PerfilDAO perfDAO = new PerfilDAO(em);

            bw = new BufferedWriter(new FileWriter("reporte.txt"));
            bw.write("nombre | apellidos | email | username \n");

            for (Usuario u: listausUarios) {
                Perfil perfil = perfDAO.buscarPorIdUsuario(u.getUsuarioId());
                bw.write(u.getNombre() + " | " + u.getApellidos() + " | " + u.getEmail() + " | " + perfil.getUsername());
            }

            LOG.debug("Reporte generado exitosamente.");

        } catch (RuntimeException e) {
            LOG.error("Error durante la generación del reporte: " + e.getMessage());
        } catch (IOException e) {
            LOG.error("Error en la estructura del reporte: " + e.getMessage());
        }
        finally {
            try
            {
                if (em != null && em.isOpen()) {
                    em.close();
                    LOG.debug("EntityManager cerrado.");
                }
            }
            catch (RuntimeException e)
            {
                LOG.error("Error durante el cierre del EtityManager: " + e.getMessage());
            }
        }
    }
    
}
