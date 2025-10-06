package fabrica.lineaDemo.DTOS;

public class InfoGeneralDTO {

    private String ordenProduccion;
    private String versionFormula;
    private String codigoProducto; //el producto a fabricar( no el padre).

    public InfoGeneralDTO(){}
    public InfoGeneralDTO (String ordenProduccion,String versionFormula,String codigoProducto){
        this.codigoProducto = codigoProducto;
        this.versionFormula = versionFormula;
        this.ordenProduccion = ordenProduccion;
    }


    public void setOrdenProduccion(String ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public String getOrdenProduccion() {
        return ordenProduccion;
    }

    public String getVersionFormula() {
        return versionFormula;
    }

    public void setVersionFormula(String versionFormula) {
        this.versionFormula = versionFormula;
    }
}
