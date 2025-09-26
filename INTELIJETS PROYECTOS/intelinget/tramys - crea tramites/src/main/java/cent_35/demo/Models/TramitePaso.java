package cent_35.demo.Models;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class TramitePaso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tramite_id")
    private Tramite tramite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="paso_id")
    private Paso paso;

    private Integer orden; // el orden de los pasos

    public TramitePaso(){}

    public TramitePaso(Tramite tramite, Paso paso, Integer orden){
        this.tramite = tramite;
        this.paso = paso;
        this.orden = orden;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
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
