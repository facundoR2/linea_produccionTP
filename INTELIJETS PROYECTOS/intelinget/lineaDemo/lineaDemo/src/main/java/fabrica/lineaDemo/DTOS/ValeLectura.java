package fabrica.lineaDemo.DTOS;

import fabrica.lineaDemo.Models.Producto;
import fabrica.lineaDemo.Models.Puesto;
import fabrica.lineaDemo.Models.Usuario;
import fabrica.lineaDemo.Models.ValeProduccion;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class ValeLectura {


    private Long idTrazado;

    private String codigoProducto; //serial del producto terminado.


    private ValeProduccion valeProduccion;


    private Puesto puesto;


    private Producto componente;

    private LocalDateTime tiempo;


    private Usuario usuario;

    private Integer estado; // 1=completado; 2=en proceso, 5 = error

    //getters y setters


    public Long getIdTrazado() {
        return idTrazado;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setComponente(Producto componente) {
        this.componente = componente;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getEstado() {
        return estado;
    }

    public ValeLectura(){}

}
