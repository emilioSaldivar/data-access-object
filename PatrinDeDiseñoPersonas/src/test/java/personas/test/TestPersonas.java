
package personas.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import personas.dto.PersonaDTO;
import personas.jdbc.Conexion;
import personas.jdbc.PersonaDao;
import personas.jdbc.PersonaDaoJDBC;


public class TestPersonas {

   
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit() == true) {
                conexion.setAutoCommit(false);
            }
            PersonaDaoJDBC personaJdbc = new PersonaDaoJDBC(conexion);
            
            List<PersonaDTO> personas = personaJdbc.seleccionar();
            
            personas.forEach(personaDatos -> {
                System.out.println("personaDatos = " + personaDatos);
            });
            
            conexion.commit();
            System.out.println("Se ha hecho cmmit de la transaccion");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback\n");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
                System.out.println("No se ejecuto e rollback");
            }
        }
    }
    
}
