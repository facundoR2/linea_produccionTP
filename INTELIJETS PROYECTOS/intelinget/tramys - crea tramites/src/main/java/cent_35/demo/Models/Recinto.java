package cent_35.demo.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Recinto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private double lat;
    private double lng;

    private String nombre;

    private String direccion;

    private String hAtencion;
    private String hCierre;

    private boolean activo = true; //agregado nuevo atributo para borrado logico.

    @ManyToOne
    @JoinColumn(name = "creador_id")
    private Usuario creador;

    @OneToMany(mappedBy = "recinto", fetch = FetchType.EAGER)
    Set<Tramite> tramites = new HashSet<>();


    public Recinto (){}

    public Recinto( double lat,double lng,String nombre,String direccion,String hAtencion,String hCierre){
        this.lat = lat;
        this.lng = lng;
        this.nombre = nombre;
        this.direccion = direccion;
        this.hAtencion = hAtencion;
        this.hCierre = hCierre;
        this.activo = true; //nuevo, por las dudas no se agrege a la BD.
    }

    public Set<Tramite> getTramites(){
        return tramites;
    }

    public void addTramite(Tramite tramite){
        tramite.setRecinto(this);
        tramites.add(tramite);
    }




    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String gethAtencion() {
        return hAtencion;
    }

    public void sethAtencion(String hAtencion) {
        this.hAtencion = hAtencion;
    }

    public String gethCierre() {
        return hCierre;
    }

    public void sethCierre(String hCierre) {
        this.hCierre = hCierre;
    }

    public boolean isActivo(){
        return activo; //funcion get para boolean activo.
    }

    public void setActivo(boolean activo){
        this.activo = activo; //funcion set para boolean activo.
    }
}
