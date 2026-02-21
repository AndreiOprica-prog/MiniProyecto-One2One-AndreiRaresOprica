package es.ciudadescolar;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ciudadescolar.dominio.modelo.Usuario;
import es.ciudadescolar.servicios.UsuarioService;
import es.ciudadescolar.util.JPAUtil;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EntityManager;

public class Main
{
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.debug("Inicio de la aplicación: ");
        
        try
        {
            UsuarioService user_service = new UsuarioService();
            
            // LocalDate fechaAndrei = LocalDate.of(2005, 9, 12);
            // user_service.registrarUsuario("Andrei Rares", "Oprica", fechaAndrei, "andrei.oprica@educa.madrid.org", "Opricus40", "ahfahfgayfafv");

            // LocalDate fechaDiego = LocalDate.of(2006, 12, 15);
            // user_service.registrarUsuario("Diego", "Ayensa Angulo", fechaDiego, "diego.ayensa@educa.madrid.org", "Diekote", "mibombo");
            
            // LocalDate fechaAlby = LocalDate.of(2006, 9, 6);
            // user_service.registrarUsuario("Alberto", "Postigo Mora", fechaAlby, "alberto.postigo@educa.madrid.org", "Albyyy", "punpun");

            user_service.eliminarUsuario(4);

            // user_service.modificarPerfilUsuario(4, "Alberto diente");

            // user_service.generarReporte();
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