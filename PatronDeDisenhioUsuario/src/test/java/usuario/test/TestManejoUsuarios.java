package usuario.test;

import java.sql.Connection;
import java.sql.SQLException;
import usuario.dto.UsuarioDto;
import usuario.jdbc.Conexion;
import usuario.jdbc.UsuarioDAO;



public class TestManejoUsuarios {

    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit() == true) {
                conexion.setAutoCommit(false);
            }
            
            UsuarioDAO usuarioDao = new UsuarioDAO(conexion);
            
            //actualizamos un registro
            UsuarioDto cambioUsuario = new UsuarioDto("anibalote", "anibalote123");
            cambioUsuario.setIdUsuario(2);
            
            
            usuarioDao.actualizar(cambioUsuario);
            
            
            //insertamos un nuevo registro
            UsuarioDto nuevoUsuario = new UsuarioDto("Anibal Jesus", "minianibalito123");
            
            usuarioDao.insertar(nuevoUsuario);

            conexion.commit();
            System.out.println("Se ha hecho cmmit de la transaccion");
          
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback\n");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
                System.out.println("No se ejecuto e rollback");
            }
        }

    }

}
