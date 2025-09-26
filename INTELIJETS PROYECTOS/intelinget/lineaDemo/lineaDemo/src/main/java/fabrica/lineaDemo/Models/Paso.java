package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

@Entity
public class Paso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paso")
    private Long idPaso;

    private String nombre;

    private String descripcion;



    private Integer duracion;

    public Paso(){}

    public Paso(String nombre, String descripcion, Integer duracion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    //getters y setters


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

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
}
