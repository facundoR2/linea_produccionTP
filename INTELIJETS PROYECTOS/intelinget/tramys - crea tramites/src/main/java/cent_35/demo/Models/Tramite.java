package cent_35.demo.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Tramite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String nombre;

    private TramiteEstado estado;

    // union con pasos
    @OneToMany(mappedBy = "tramite", fetch = FetchType.EAGER)
    private Set<TramitePaso> tramitePasos = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recinto_id")
    private Recinto recinto;

    public Tramite(){}

    public Tramite(String nombre,Recinto recinto){
        this.nombre = nombre;
        this.estado = TramiteEstado.ACTIVO;
        setRecinto(recinto);
    }

    public void setRecinto(Recinto recinto){
        this.recinto = recinto;
    }



    public void addTramitePaso(TramitePaso tramitePaso){
        tramitePaso.setTramite(this);
        tramitePasos.add(tramitePaso);
    }

    public Set<Paso> getPasos() { //buscar los pasos desde el tramite.
        return tramitePasos.stream().map(tramitePaso -> tramitePaso.getPaso()).collect(Collectors.toSet());
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
