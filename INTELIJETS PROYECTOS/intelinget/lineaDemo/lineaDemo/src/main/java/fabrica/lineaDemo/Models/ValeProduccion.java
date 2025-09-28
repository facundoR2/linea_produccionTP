package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ValeProduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVale;



    @ManyToOne
    @JoinColumn(name = "FK_ID_OP")
    private OrdenProduccion ordenProduccion;

    @ManyToOne
    @JoinColumn(name = "codigoFormula")
    private Formula formula;

    private LocalDate fechaRegistro;

    private Integer estado; // 1= completado, 2= en espera, 3 = error

    @OneToMany(mappedBy = "valeProduccion",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ValeProduccionDetalle> detalles = new ArrayList<>();

    //getters y setters


    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setOrdenProduccionDetalles(OrdenProduccion ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
    }

    public OrdenProduccion getOrdenProduccion() {
        return ordenProduccion;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setIdVale(Integer idVale) {
        this.idVale = idVale;
    }

    public Integer getIdVale() {
        return idVale;
    }

    public void setOrdenProduccion(OrdenProduccion ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
    }

    public List<ValeProduccionDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<ValeProduccionDetalle> detalles) {
        this.detalles = detalles;
    }
}
