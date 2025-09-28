package fabrica.lineaDemo.DTOS;

public class UsuarioDTO {
    private Integer id;
    private String username;
    private String nombre;

    public UsuarioDTO() {}

    public UsuarioDTO(Integer id, String username, String nombre) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
    }

    // getters / setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
