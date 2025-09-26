package cent_35.demo.Dtos;

public class CrearUsuarioDTO {

    private String nombre;
    private String apellido;
    private String password;
    private String correo;

    public CrearUsuarioDTO(){}

    public CrearUsuarioDTO(String nombre, String apellido, String password, String correo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
