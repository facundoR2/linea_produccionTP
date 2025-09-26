package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ValeDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrazado;

    private String codigoProducto; //serial del producto terminado.

    @ManyToOne
    @JoinColumn(name = "FK_ID_produccion")
    private ValeProduccion valeProduccion;

    @ManyToOne
    @JoinColumn(name = "FK_ID_puesto")
    private Puesto puesto;

    @ManyToOne
    @JoinColumn(name = "FK_ID_Componente")
    private Producto componente;

    private LocalDateTime tiempo;

    @ManyToOne
    @JoinColumn(name = "FK_ID_usuario")
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


    public ValeProduccion getValeProduccion() {
        return valeProduccion;
    }

    public void setValeProduccion(ValeProduccion valeProduccion) {
        this.valeProduccion = valeProduccion;
    }

    public LocalDateTime getTiempo() {
        return tiempo;
    }

    public void setTiempo(LocalDateTime tiempo) {
        this.tiempo = tiempo;
    }

    public Producto getComponente() {
        return componente;
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

    public void setIdTrazado(Long idTrazado) {
        this.idTrazado = idTrazado;
    }

}
