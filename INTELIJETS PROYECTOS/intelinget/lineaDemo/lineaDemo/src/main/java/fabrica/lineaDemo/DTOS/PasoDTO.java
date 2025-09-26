package fabrica.lineaDemo.DTOS;

public class PasoDTO {

    private String nombre;

    private String descripcion;

    private String herramientas;


    public PasoDTO(String nombre, String descripcion, String herramientas){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.herramientas = herramientas;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getHerramientas() {
        return herramientas;
    }

    public void setHerramientas(String herramientas) {
        this.herramientas = herramientas;
    }
}
