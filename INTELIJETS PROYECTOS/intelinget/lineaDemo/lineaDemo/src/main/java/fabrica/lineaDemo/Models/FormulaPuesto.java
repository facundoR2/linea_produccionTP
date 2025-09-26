package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Formula_puestos")
public class FormulaPuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_FP;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFD", referencedColumnName = "idFD")
    private FormulaDetalle formulaDetalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idP", referencedColumnName = "idP")
    private Puesto puesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPP", referencedColumnName = "idPP")
    private ProcesoPaso procesoPaso;

    public FormulaPuesto(){}

    public FormulaPuesto(FormulaDetalle formulaDetalle, Puesto puesto, ProcesoPaso procesoPaso){
        this.formulaDetalle = formulaDetalle;
        this.puesto = puesto;
        this.procesoPaso = procesoPaso;
    }

    //Getters y setters


    public FormulaDetalle getFormulaDetalle() {
        return formulaDetalle;
    }

    public void setFormulaDetalle(FormulaDetalle formulaDetalle) {
        this.formulaDetalle = formulaDetalle;
    }

    public ProcesoPaso getProcesoPaso() {
        return procesoPaso;
    }

    public void setProcesoPaso(ProcesoPaso procesoPaso) {
        this.procesoPaso = procesoPaso;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public Integer getId_FP() {
        return id_FP;
    }

    public void setId_FP(Integer id_FP) {
        this.id_FP = id_FP;
    }
}
