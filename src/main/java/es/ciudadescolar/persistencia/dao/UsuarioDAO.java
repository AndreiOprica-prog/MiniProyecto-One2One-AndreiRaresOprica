package es.ciudadescolar.persistencia.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ciudadescolar.dominio.modelo.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UsuarioDAO
{
    private static final Logger LOG = LoggerFactory.getLogger(Usuario.class);

    private final EntityManager em;

    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }

    public void guardar(Usuario user) {
        em.persist(user);
    }

    public void actualizar(Usuario user) {
        em.merge(user);
    }

    public void eliminar(Usuario user) {
        em.remove(user);
    }

    public void actualizarEmailUsuario(Long idUsuario, String emailNuevo) {
        Usuario userBuscado = em.find(Usuario.class, idUsuario);
        if (userBuscado != null) {
            userBuscado.setEmail(emailNuevo);
        }
    }

    public void actualizarNombreUsuario(Long idUsuario, String nombre) {
        Usuario userBuscado = em.find(Usuario.class, idUsuario);
        if (userBuscado != null) {
            userBuscado.setNombre(nombre);
        }
    }

    public void actualizarApelliodsUsuario(Long idUsuario, String apellidos) {
        Usuario userBuscado = em.find(Usuario.class, idUsuario);
        if (userBuscado != null) {
            userBuscado.setApellidos(apellidos);
        }
    }

    public void actualizarfechaUsuario(Long idUsuario, LocalDate fecha) {
        Usuario userBuscado = em.find(Usuario.class, idUsuario);
        if (userBuscado != null) {
            userBuscado.setFecha_nacimiento(fecha);;
        }
    }

    public void eliminarPorId(Integer idUsuario) {
        Usuario userAEliminar = null;

        TypedQuery<Usuario> consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.usuarioId = :id", Usuario.class);
        consulta.setParameter("id", idUsuario);

        List<Usuario> lista = consulta.getResultList();

        if (lista.isEmpty()) {
            LOG.warn("No hay ningún usuario con ese ID: " + idUsuario);
        } else {
            userAEliminar = lista.get(0);
        }

        em.remove(userAEliminar);
        LOG.debug("Usuario con id " + userAEliminar.getUsuarioId() + " eliminado.");
    }

    public Usuario buscarPorEmail(String email) {
        TypedQuery<Usuario> consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :correo", Usuario.class);

        consulta.setParameter("correo", email);

        List<Usuario> lista = consulta.getResultList();

        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public Usuario buscarPorId(Integer usuarioId) {
        TypedQuery<Usuario> consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.usuarioId = :id", Usuario.class);
        consulta.setParameter("id", usuarioId);

        List<Usuario> lista = new ArrayList<>();

        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public List<Usuario> recuperarTodosusuarios() {
        TypedQuery<Usuario> consulta = em.createQuery("Select u FROM Usuario u", Usuario.class);
        List<Usuario> lista = consulta.getResultList();

        if (!lista.isEmpty()) {
            return lista;
        } else {
            LOG.warn("No se ha recuperado ningún usuario.");
            return null;
        }
    }

}
