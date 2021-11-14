
package usuario.dto;


public class UsuarioDto {
    private int idUsuario;
    private String username;
    private String password;
    
    public UsuarioDto(int idUsuario){
        this.idUsuario = idUsuario;
    }

    public UsuarioDto(String usuario, String password) {
        this.username = usuario;
        this.password = password;
    }

    public UsuarioDto(int idUsuario, String usuario, String password) {
        this.idUsuario = idUsuario;
        this.username = usuario;
        this.password = password;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", usuario=" + username + ", password=" + password + '}';
    }
    
    
}
