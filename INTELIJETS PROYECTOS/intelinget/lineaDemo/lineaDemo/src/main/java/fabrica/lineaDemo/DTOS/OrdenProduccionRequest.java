package fabrica.lineaDemo.DTOS;

import java.util.List;

public class OrdenProduccionRequest {
    private Integer idOrdenProduccion;
    private List<ComponenteRequest> componentes;

    public static class ComponenteRequest{
        private Integer idProducto;
        private Integer cantNecesaria;

        public Integer getIdProducto() {
            return idProducto;
        }
        public void setIdProducto(Integer idProducto) {
            this.idProducto = idProducto;
        }
        public Integer getCantNecesaria() {
            return cantNecesaria;
        }
        public void setCantNecesaria(Integer cantNecesaria) {
            this.cantNecesaria = cantNecesaria;
        }
    }
    //G yS


    public void setComponentes(List<ComponenteRequest> componentes) {
        this.componentes = componentes;
    }

    public List<ComponenteRequest> getComponentes() {
        return componentes;
    }

    public Integer getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(Integer idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }
}
