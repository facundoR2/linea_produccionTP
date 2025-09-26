package cent_35.demo.Dtos;

import java.util.List;

public class RecintoDTO {
    private double lat;
    private double lng;

    private String nombre;

    private String direccion;

    private String hAtencion;
    private String hCierre;




    public RecintoDTO(){}

    public RecintoDTO(double lat,double lng, String nombre,String direccion, String hAtencion,String hCierre){
        this.lat = lat;
        this.lng = lng;
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
