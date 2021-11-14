package usuario.jdbc;


import usuario.dto.UsuarioDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection conexionTransaccional = null;

    private static final String SQL_SELECT = "SELECT id_usuario, username, password FROM test.usuario";
    private static final String SQL_INSERT = "INSERT INTO test.usuario (username, password) VALUES(?, ?)";
    private static final String SQL_DELETE = "DELETE FROM test.usuario WHERE id_usuario = ?";
    private static final String SQL_UPDATE = "UPDATE test.usuario SET username = ?, password = ? WHERE id_usuario = ?";

    //constructores 
    public UsuarioDAO() {
    }

    public UsuarioDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    //metodos
    public List<UsuarioDto> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioDto usuario = null;
        List<UsuarioDto> usuarios = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            System.out.println("Ejecutando consulta a la base de datos");
            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String username = rs.getString("username");
                String password = rs.getString("password");

                usuario = new UsuarioDto(idUsuario, username, password);

                usuarios.add(usuario);

            }
        } finally {

            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);

        }
        return usuarios;
    }

    public int insertar(UsuarioDto usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());

            System.out.println("Ejecutando consulta a la base de datos:  " + SQL_INSERT);
            registros = stmt.executeUpdate();
            System.out.println("Registros afectados: " + registros);
        } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }

        }
        return registros;
    }

    public int actualizar(UsuarioDto usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());
            
            System.out.println("Ejecutando QUERY " + SQL_UPDATE);
            registros = stmt.executeUpdate();
            System.out.println("Registros afectados: " + registros);
        } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return registros;
    }

    public int borrar(UsuarioDto usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getIdUsuario());
            
            System.out.println("Ejecutando query: " + SQL_DELETE);
            registros = stmt.executeUpdate();

            System.out.println("Registros eliminados: " + registros);
        }
        finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return registros;
    }
}
