package es.ciudadescolar.persistencia.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import es.ciudadescolar.dominio.modelo.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UsuarioDAO
{
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

    public void eliminarPorId(Long idUsuario) {
        Usuario userAEliminar = em.find(Usuario.class, idUsuario);

        if (userAEliminar != null) {
            em.remove(userAEliminar);
        }
    }

    public void eliminar(Usuario user) {
        Usuario userAEliminar = em.find(Usuario.class, user.getUsuarioId());

        if (user != null) {
            em.remove(userAEliminar);
        }
    }

    public Usuario buscarPorEmail(String email) {
        TypedQuery<Usuario> consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :correo", Usuario.class);

        consulta.setParameter("correo", email);

        List<Usuario> lista = new ArrayList<>();

        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }



}
