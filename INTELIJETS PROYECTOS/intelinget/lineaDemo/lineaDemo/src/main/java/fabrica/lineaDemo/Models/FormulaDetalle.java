package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "formula_detalle", schema = "practico_fabrica")
public class FormulaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFD;

    //relacion con Formula (N:1)
    @ManyToOne
    @JoinColumn(name = "codigo_formula", referencedColumnName = "codigoFormula")
    private Formula formula;


    @Column(name = "nro_version", length = 10)
    private String version;

    @Column(length = 200)
    private String Nproducto; //codigo del producto que crea la formula

    @Column(length = 100)
    private String variacion; // version de la formula

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto componente; //nombre de la parte (dentro del productos


    private Integer cantidad;


    //Relacion con Puesto (N:1) indirecta en formula_puestos.

    public FormulaDetalle() {}

    public FormulaDetalle(Formula formula, String version, String Nproducto, String variacion, Producto componente, Integer cantidad) {
        this.formula = formula;
        this.version = version;
        this.Nproducto = Nproducto;
        this.variacion = variacion;
        this.componente = componente;
    }

    //getters y setters.


    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Producto getComponente() {
        return componente;
    }

    public void setComponente(Producto componente) {
        this.componente = componente;
    }

    public String getNproducto() {
        return Nproducto;
    }

    public void setNproducto(String nproducto) {
        Nproducto = nproducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getVariacion() {
        return variacion;
    }

    public void setVariacion(String variacion) {
        this.variacion = variacion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}