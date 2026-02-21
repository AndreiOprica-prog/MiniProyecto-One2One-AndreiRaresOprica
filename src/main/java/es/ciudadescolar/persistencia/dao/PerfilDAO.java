package es.ciudadescolar.persistencia.dao;

// import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ciudadescolar.dominio.modelo.Perfil;
// import es.ciudadescolar.dominio.modelo.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PerfilDAO
{
    private static final Logger LOG = LoggerFactory.getLogger(PerfilDAO.class);
    private final EntityManager em;

    public PerfilDAO(EntityManager em) {
        this.em = em;
    }

    public void guardar(Perfil perf) {
        em.persist(perf);
    }

    public void actualizar(Perfil perf) {
        em.merge(perf);
    }

    public void eliminar(Perfil perf) {
        em.remove(perf);
    }

    public void actualizarUsernamePerfil(Long idPerfil, String usernameNuevo) {
        Perfil perfilBuscado = em.find(Perfil.class, idPerfil);
        if (perfilBuscado != null) {
            perfilBuscado.setUsername(usernameNuevo);;
        }
    }

    public void actualizarPasswordHashPerfil(Long idPerfil, String pswd) {
        Perfil perfilBuscado = em.find(Perfil.class, idPerfil);
        if (perfilBuscado != null) {
            perfilBuscado.setContrasenia(pswd);
        }
    }

    public void eliminarPorId(Long idPerfil) {
        Perfil perfilBuscado = em.find(Perfil.class, idPerfil);

        if (perfilBuscado != null) {
            em.remove(perfilBuscado);
        }
    }

    public void eliminarPoId(Perfil perf) {
        em.remove(perf);
    }

    public Perfil buscarPorUsername(String username) {
        TypedQuery<Perfil> consulta = em.createQuery("SELECT p FROM Perfil p WHERE p.username = :nombreusuario", Perfil.class);

        consulta.setParameter("nombreusuario", username);

        List<Perfil> lista = new ArrayList<>();

        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    } 
    
    public void modificarUsername(Perfil perf, String username) {
        Perfil perfAModificar = em.find(Perfil.class, perf);
        perfAModificar.setUsername(username);
        LOG.debug("Username del perfil modificado a: " + perfAModificar.getUsername());
    }

    public Perfil buscarPorIdUsuario(Integer idUsuario) {
        TypedQuery<Perfil> consulta = em.createQuery("SELECT p FROM Perfil p WHERE p.usuario = :idUsuario", Perfil.class);
        consulta.setParameter("idUsuario", idUsuario);
        List<Perfil> listaPerfil = consulta.getResultList();

        if (!listaPerfil.isEmpty()) {
            return listaPerfil.get(0);
        } else {
            LOG.warn("No existe ningún usuario cuyo perfil tenga el ID: " + idUsuario);
            return null;
        }
    }

}
