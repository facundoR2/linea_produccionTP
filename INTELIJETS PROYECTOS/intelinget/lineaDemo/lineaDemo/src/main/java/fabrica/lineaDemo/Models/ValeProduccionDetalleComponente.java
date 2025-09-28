package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

@Entity
public class ValeProduccionDetalleComponente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComponente;

    private String nombre;
    private String codigoProducto;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "vale_detalle_id",referencedColumnName = "idVPD")
    private ValeProduccionDetalle valeProduccionDetalle;




    //GYS


    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setId(Integer id) {
        this.idComponente = id;
    }

    public long getId() {
        return idComponente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public ValeProduccionDetalle getValeProduccionDetalle() {
        return valeProduccionDetalle;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setValeProduccionDetalle(ValeProduccionDetalle valeProduccionDetalle) {
        this.valeProduccionDetalle = valeProduccionDetalle;
    }

}
