package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class ValeProduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVale;

    private Integer idOperacion;

    @ManyToOne
    @JoinColumn(name = "FK_ID_OP")
    private OrdenProduccion ordenProduccion;

    @ManyToOne
    @JoinColumn(name = "codigoFormula")
    private Formula formula;

    private LocalDate fechaRegistro;

    private Integer estado; // 1= completado, 2= en espera, 3 = error

    @OneToMany(mappedBy = "valeProduccion")
    private List<ValeDetalle> detalles;

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

    public Integer getIdVale() {
        return idVale;
    }

    public void setIdVale(Integer idVale) {
        this.idVale = idVale;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public List<ValeDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<ValeDetalle> detalles) {
        this.detalles = detalles;
    }
}
