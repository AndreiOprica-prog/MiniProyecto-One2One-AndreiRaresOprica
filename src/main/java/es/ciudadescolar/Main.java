package es.ciudadescolar;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ciudadescolar.dominio.modelo.Usuario;
import es.ciudadescolar.servicios.PerfilService;
import es.ciudadescolar.servicios.UsuarioService;
import es.ciudadescolar.util.JPAUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

public class Main
{
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.debug("Inicio de la aplicación: ");
        
        try
        {
            UsuarioService user_service = new UsuarioService();
            LocalDate fechaAndrei = LocalDate.of(2005, 9, 12);
            Long idUsuario = user_service.registrarusuario("Andrei Rares", "Oprica", fechaAndrei, "andrei.oprica@educa.madrid.org");

            // PerfilService perf_service = new PerfilService();
            // Long idPerfil = perf_service.registrarPerfilConUsuario("Opricus06", "gshisfpasabsibs", idUsuario);
        }
        catch (Exception e)
        {
            LOG.error("Error en la aplicación: " + e.getMessage());
        }
        finally
        {
            JPAUtil.close();
        }

        LOG.debug("Fin de la aplicación");
    }
}