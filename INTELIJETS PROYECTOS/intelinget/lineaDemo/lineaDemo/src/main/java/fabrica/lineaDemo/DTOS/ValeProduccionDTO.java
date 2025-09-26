package fabrica.lineaDemo.DTOS;

import java.util.List;

public class ValeProduccionDTO {

    private String codigoProducto;

    private List<ProductoDTO> Componentes;

    public ValeProduccionDTO(){}

    public ValeProduccionDTO (List<ProductoDTO> componentes, String codigoProducto){
        this.codigoProducto = codigoProducto;
        this.Componentes = componentes;
    }


    //Getters y setters.


    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public List<ProductoDTO> getComponentes() {
        return Componentes;
    }

    public void setComponentes(List<ProductoDTO> componentes) {
        Componentes = componentes;
    }
}
