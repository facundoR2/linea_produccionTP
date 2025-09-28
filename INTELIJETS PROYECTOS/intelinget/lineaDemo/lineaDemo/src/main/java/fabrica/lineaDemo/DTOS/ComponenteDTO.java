package fabrica.lineaDemo.DTOS;

public class ComponenteDTO {

    private String nombre; //nombre del componente

    private String codigoProducto; //codigo del componente

    private Integer cantidad;


    public ComponenteDTO (String nombre, String codigoProducto, Integer cantidad){
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    //GYS


    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
