package es.ciudadescolar.servicios;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ciudadescolar.dominio.modelo.Perfil;
import es.ciudadescolar.dominio.modelo.Usuario;
import es.ciudadescolar.persistencia.dao.PerfilDAO;
import es.ciudadescolar.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PerfilService
{
    private static final Logger LOG = LoggerFactory.getLogger(PerfilService.class);

    public Long registrarPerfilConUsuario(String username, String passwordHash, Long id_uduario) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Long idPerfil = -1L;

        try {
            trans.begin();

            PerfilDAO perfDao = new PerfilDAO(em);

            Perfil perf = perfDao.buscarPorUsername(username);

            if (perf == null) {
                perf = new Perfil(username, passwordHash);

                perf.setUsuario(id_uduario);

                em.persist(perf);
            } else {
                LOG.warn("Ya existe un perfil con el username " + username + " en la BD.");
            }

            trans.commit();

            LOG.debug("Perfil registrado: " + perf.toString());

            idPerfil = perf.getId_perfil();
        } catch (RuntimeException e) {
            LOG.error("Error durante la transacción: " + e.getMessage());

            if (trans != null && trans.isActive()) {
                trans.rollback();
                LOG.debug("Rollback de la transacción");
            }

            throw e;
        } finally {
            try {
                if (em != null && em.isOpen()) {
                    em.close();
                    LOG.debug("EntityManager cerrado.");
                }
            } catch (RuntimeException e) {
                LOG.debug("Error durante el cierre del EntityManager.");
            }
        }

        return idPerfil;
    }

}
