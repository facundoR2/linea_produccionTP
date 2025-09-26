package cent_35.demo.Dtos;

public class PasoDTO {
    private String nombre;
    private String descripcion;


    public PasoDTO(){}


    public PasoDTO(String nombre,String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
