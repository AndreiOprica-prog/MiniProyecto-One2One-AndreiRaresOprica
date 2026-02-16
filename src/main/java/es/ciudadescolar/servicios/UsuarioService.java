package es.ciudadescolar.servicios;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ciudadescolar.dominio.modelo.Usuario;
import es.ciudadescolar.persistencia.dao.UsuarioDAO;
import es.ciudadescolar.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UsuarioService
{
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioService.class);

    public void registrarusuario(String nombre, String apellidos, LocalDate fecha_nacimiento, String email) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try
        {
            trans.begin();

            UsuarioDAO userDAO = new UsuarioDAO(em);

            Usuario user = userDAO.buscarPorEmail(email);

            if (user == null) {
                user = new Usuario(nombre, apellidos, fecha_nacimiento, email);
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
    
}
