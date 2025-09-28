package fabrica.lineaDemo.DTOS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ValeLectura {

    private String idTrazado; // identificador unico de instancia (Cproducto-nrounico)

    private String codigoProducto; //serial del producto terminado.

    private Integer puestoActual; //el puesto en el que se encuentra actualmente.

    private LocalDateTime tiempo = LocalDateTime.now();


    private List<ComponenteDTO> componentes = new ArrayList<>();

    private UsuarioDTO usuario;

    public ValeLectura(){};


    public ValeLectura(String idTrazado, String codigoProducto,Integer puestoActual,
                       List<ComponenteDTO> componentes, UsuarioDTO usuario){
        this.idTrazado = idTrazado;
        this.codigoProducto = codigoProducto;
        this.puestoActual = puestoActual;
        this.componentes = componentes;
        this.usuario = usuario;
        this.tiempo = LocalDateTime.now();
    }

    //getters y setters


    public void setIdTrazado(String idTrazado) {
        this.idTrazado = idTrazado;
    }

    public String getIdTrazado() {
        return idTrazado;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public List<ComponenteDTO> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<ComponenteDTO> componentes) {
        this.componentes = componentes;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setTiempo(LocalDateTime tiempo) {
        this.tiempo = tiempo;
    }

    public LocalDateTime getTiempo() {
        return tiempo;
    }

    public Integer getPuestoActual() {
        return puestoActual;
    }

    public void setPuestoActual(Integer puestoActual) {
        this.puestoActual = puestoActual;
    }
}
