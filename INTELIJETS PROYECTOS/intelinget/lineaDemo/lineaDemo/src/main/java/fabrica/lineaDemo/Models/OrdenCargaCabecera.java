package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

@Entity
public class OrdenCargaCabecera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOCC;

    private Integer cantProducida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOPC",referencedColumnName = "idOPC")
    private OrdenProduccionDetalles ordenProduccionDetalles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_formula",referencedColumnName = "codigoFormula")
    private Formula formula;

    private String estado; // "pendiente";"en produccion"; "producido"




    public OrdenCargaCabecera(){}


    //GYS

    public void setIdOCC(Integer idOCC) {
        this.idOCC = idOCC;
    }

    public Integer getIdOCC() {
        return idOCC;
    }

    public OrdenProduccionDetalles getOrdenProduccionDetalles() {
        return ordenProduccionDetalles;
    }

    public void setOrdenProduccionDetalles(OrdenProduccionDetalles ordenProduccionDetalles) {
        this.ordenProduccionDetalles = ordenProduccionDetalles;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
