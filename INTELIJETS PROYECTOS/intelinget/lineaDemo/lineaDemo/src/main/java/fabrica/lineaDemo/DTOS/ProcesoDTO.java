package fabrica.lineaDemo.DTOS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcesoDTO {

    private String nombre;

    private Date fecha_alta;

    private List<PasoDTO> pasos = new ArrayList<>();

    private String codFormula;


    public ProcesoDTO(){}

    public ProcesoDTO(String nombre, Date fecha_alta, List<PasoDTO> pasos, String codFormula){
        this.nombre = nombre;
        this.fecha_alta = fecha_alta;
        this.codFormula = codFormula;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public void setPasos(List<PasoDTO> pasos) {
        this.pasos = pasos;
    }

    public List<PasoDTO> getPasos() {
        return pasos;
    }

    public String getCodFormula() {
        return codFormula;
    }

    public void setCodFormula(String codFormula) {
        this.codFormula = codFormula;
    }
}
