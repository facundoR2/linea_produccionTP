package cent_35.demo.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String nombre;
    private String Contenido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paso_id")
    private Paso paso;


    public Documento() {
    }

    public Documento(String nombre, String contenido) {
        this.nombre = nombre;
        this.Contenido = contenido;

    }

    public void setPaso(Paso paso){
        this.paso = paso;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        Contenido = contenido;
    }

}