package fabrica.lineaDemo.DTOS;

import java.util.List;

public class ValeProduccionDTO {

    private String codigoProducto; //codigo del producto padre

    private List<ComponenteDTO> Componentes; //lista de los componentes anexados.

    public ValeProduccionDTO(){}

    public ValeProduccionDTO (List<ComponenteDTO> componentes, String codigoProducto){
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

    public List<ComponenteDTO> getComponentes() {
        return Componentes;
    }

    public void setComponentes(List<ComponenteDTO> componentes) {
        Componentes = componentes;
    }
}
