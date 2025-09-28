package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ValeProduccionDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVPD;

    private String codigoProducto; //serial del producto terminado.

    private String idTrazado;
    private Integer puestoFinal;

    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "vale_produccion_id", referencedColumnName = "idVale")
    private ValeProduccion valeProduccion;


    @OneToMany(mappedBy = "valeProduccionDetalle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ValeProduccionDetalleComponente> componentes = new ArrayList<>();


    private Integer estado; // 1=completado; 2=en proceso, 5 = error

    //getters y setters


    public String getIdTrazado() {
        return idTrazado;
    }

    public void setIdTrazado(String idTrazado) {
        this.idTrazado = idTrazado;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ValeProduccion getValeProduccion() {
        return valeProduccion;
    }

    public void setValeProduccion(ValeProduccion valeProduccion) {
        this.valeProduccion = valeProduccion;
    }

    public void setComponentes(List<ValeProduccionDetalleComponente> componentes) {
        this.componentes = componentes;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getPuestoFinal() {
        return puestoFinal;
    }

    public void setPuestoFinal(Integer puestoFinal) {
        this.puestoFinal = puestoFinal;
    }

    public Integer getIdVPD() {
        return idVPD;
    }

    public void setIdVPD(Integer idVPD) {
        this.idVPD = idVPD;
    }

    public List<ValeProduccionDetalleComponente> getComponentes() {
        return componentes;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

}