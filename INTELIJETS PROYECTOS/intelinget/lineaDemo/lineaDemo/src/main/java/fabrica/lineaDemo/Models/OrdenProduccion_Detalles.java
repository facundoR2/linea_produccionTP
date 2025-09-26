package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class OrdenProduccion_Detalles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOPC;

    @ManyToOne
    @JoinColumn(name = "id_producto",referencedColumnName = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_formula",referencedColumnName = "id_formula")
    private Formula formula;

    private Integer cantPrevista;
    private Integer cantProducida;
    private LocalDate fechaProduccion;

    public OrdenProduccion_Detalles(){}

    public OrdenProduccion_Detalles(Producto producto,Formula formula, Integer cantPrevista,LocalDate fechaProduccion){
        this.formula = formula;
        this.producto = producto;
        this.cantPrevista = cantPrevista;
        this.fechaProduccion = fechaProduccion;
    }

    //G Y S


    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto getProducto() {
        return producto;
    }

    public Integer getCantPrevista() {
        return cantPrevista;
    }

    public void setCantPrevista(Integer cantPrevista) {
        this.cantPrevista = cantPrevista;
    }

    public Integer getCantProducida() {
        return cantProducida;
    }

    public void setCantProducida(Integer cantProducida) {
        this.cantProducida = cantProducida;
    }

    public LocalDate getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(LocalDate fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }
}
