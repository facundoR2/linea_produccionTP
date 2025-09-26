package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "formula", schema = "practico_fabrica")
public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_formula;

    @Column(length = 10)
    private String version;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(length = 200)
    private String producto;

    @Column(length = 200)
    private String estado;

    //relacion con formulaDetalle (1:N)
    @Column(name = "codigoFormula", unique = true,nullable = false)
    private String codigoFormula;

    //opcional
    @OneToMany(mappedBy = "formula")
    private List<FormulaDetalle> detalles;

    public Formula(){}

    public Formula(String version, Date fechaRegistro, String producto, String estado){}

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDetalles(List<FormulaDetalle> detalles) {
        this.detalles = detalles;
    }

    public List<FormulaDetalle> getDetalles() {
        return detalles;
    }

    public String getCodigoFormula() {
        return codigoFormula;
    }

    public void setCodigoFormula(String codigoFormula) {
        this.codigoFormula = codigoFormula;
    }

    public Integer getId_formula() {
        return id_formula;
    }

    public void setId_formula(Integer id_formula) {
        this.id_formula = id_formula;
    }
}
