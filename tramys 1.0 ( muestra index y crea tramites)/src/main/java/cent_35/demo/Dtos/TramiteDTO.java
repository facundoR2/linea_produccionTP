package cent_35.demo.Dtos;

import java.util.ArrayList;
import java.util.List;

public class TramiteDTO {
    private List<PasoDTO> pasos = new ArrayList<>();
    private String nombre;

    private String nombreRecinto;

    public TramiteDTO(){}



    public TramiteDTO(List<PasoDTO> pasos,String nombre,String nombreRecinto){
        this.pasos = pasos;
        this.nombre = nombre;
        this.nombreRecinto = nombreRecinto;

    }

    @Override
    public String toString() {
        return "TramiteDTO{" +
                "pasos=" + pasos +
                ", nombre='" + nombre + '\'' +
                ", nombreRecinto='" + nombreRecinto + '\'' +
                '}';
    }


    public List<PasoDTO> getPasos() {
        return pasos;
    }

    public void setPasos(List<PasoDTO> pasos) {
        this.pasos = pasos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreRecinto() {
        return nombreRecinto;
    }

    public void setNombreRecinto(String nombreRecinto) {
        this.nombreRecinto = nombreRecinto;
    }
}
