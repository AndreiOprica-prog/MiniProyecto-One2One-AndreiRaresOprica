package es.ciudadescolar;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ciudadescolar.servicios.UsuarioService;
import es.ciudadescolar.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class Main
{
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.debug("Inicio de la aplicación: ");
        
        try
        {
            UsuarioService service = new UsuarioService();

            LocalDate fechaAndrei = LocalDate.of(2005, 9, 12);

            service.registrarusuario("Andrei Rares", "Oprica", fechaAndrei, "andrei.oprica@educa.madrid.org");
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