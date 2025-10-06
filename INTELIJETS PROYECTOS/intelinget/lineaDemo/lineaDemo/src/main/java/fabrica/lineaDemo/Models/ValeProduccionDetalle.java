package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ValeProduccionDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVPD; //id de la tabla.

    private String codigoProducto; //serial del producto terminado.


    private LocalDateTime fechaHora; //fecha a la que se registró,




    //referencia al ValeProduccion del que proviene.
    @ManyToOne
    @JoinColumn(name = "vale_produccion_id", referencedColumnName = "idVale")
    private ValeProduccion valeProduccion;


    @ManyToOne
    @JoinColumn(name = "CodigoComponente",referencedColumnName = "id_producto")
    private Producto componente;

    private Integer puesto; //puesto donde se escaneo los componentes.

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario")
    private Usuario usuario;  //  usuario o operario que hizo el escaneo.


    private Integer estado; // 1=completado; 2=en proceso, 5 = error

    //getters y setters



    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }



    public ValeProduccion getValeProduccion() {
        return valeProduccion;
    }

    public void setValeProduccion(ValeProduccion valeProduccion) {
        this.valeProduccion = valeProduccion;
    }


    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }



    public Integer getIdVPD() {
        return idVPD;
    }

    public void setIdVPD(Integer idVPD) {
        this.idVPD = idVPD;
    }

    public void setComponente(Producto componente) {
        this.componente = componente;
    }

    public Producto getComponente() {
        return componente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getPuesto() {
        return puesto;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setPuesto(Integer puesto) {
        this.puesto = puesto;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}