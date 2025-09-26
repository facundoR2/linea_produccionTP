package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

@Entity
public class OrdenProduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_OP")
    private Integer idOP;


    //relacion con producto(N:1)
    @ManyToOne
    @JoinColumn(name = "FK_id_producto",referencedColumnName = "id_producto")
    private Producto producto;

    @Column(name = "cant_produccion")
    private Integer cantidad;

    //relacion con formula(N:1)
    @ManyToOne
    @JoinColumn(name = "FK_id_formula_detalle",referencedColumnName ="codigoFormula")
    private Formula formula;

    @Column(length = 50)
    private String estado; // programada, en produccion, producida

    public OrdenProduccion(){}

    public OrdenProduccion(Producto producto,Integer cantidad, Formula formula, String estado){
        this.producto = producto;
        this.formula = formula;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    //getters y settters


    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto getProducto() {
        return producto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Integer getId() {
        return idOP;
    }

    public Integer getIdOP() {
        return idOP;
    }
}
