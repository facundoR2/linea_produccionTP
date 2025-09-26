package cent_35.demo.Dtos;

public class ModRecintoDTO {
    private double lat;
    private double lng;

    private String nombreAnteriorRecinto;
    private String nombreNuevoRecinto;

    private String direccion;
    private String hAtencion;
    private String hCierre;


    public ModRecintoDTO(){}

    public ModRecintoDTO(double lat, double lng,String nombreAnteriorRecinto,String nombreNuevoRecinto, String direccion,String hAtencion,String hCierre){
        this.lat = lat;
        this.lng = lng;
        this.nombreAnteriorRecinto = nombreAnteriorRecinto;
        this.nombreNuevoRecinto = nombreNuevoRecinto;
        this.direccion = direccion;
        this.hAtencion = hAtencion;
        this.hCierre = hCierre;

    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getNombreAnteriorRecinto() {
        return nombreAnteriorRecinto;
    }

    public void setNombreAnteriorRecinto(String nombreAnteriorRecinto) {
        this.nombreAnteriorRecinto = nombreAnteriorRecinto;
    }

    public String getNombreNuevoRecinto() {
        return nombreNuevoRecinto;
    }

    public void setNombreNuevoRecinto(String nombreNuevoRecinto) {
        this.nombreNuevoRecinto = nombreNuevoRecinto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String gethAtencion() {
        return hAtencion;
    }

    public void sethAtencion(String hAtencion) {
        this.hAtencion = hAtencion;
    }

    public String gethCierre() {
        return hCierre;
    }

    public void sethCierre(String hCierre) {
        this.hCierre = hCierre;
    }
}
