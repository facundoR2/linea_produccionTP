package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Proceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProceso;

    private String nombre;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;

    @OneToMany(mappedBy = "proceso")
    private List<ProcesoPaso> pasos;

    public Proceso(){}

    public Proceso(String nombre, LocalDate fechaAlta, LocalDate fechaBaja){
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public List<ProcesoPaso> getPasos() {
        return pasos;
    }

    public void setPasos(List<ProcesoPaso> pasos) {
        this.pasos = pasos;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

}
