package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

@Entity
public class ProcesoPaso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPP")
    private Long idPP;

    @ManyToOne
    @JoinColumn(name = "idProceso")
    private Proceso proceso;

    @ManyToOne
    @JoinColumn(name = "idPaso")
    private Paso paso;

    private Integer orden;

    public ProcesoPaso(){}

    //getters y setters


    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}
