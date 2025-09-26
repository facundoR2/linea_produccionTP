package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "puestos", schema = "practico_fabrica")
public class Puesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idP;

    @Column(name = "codigo_puesto", length = 10, nullable = false)
    private String codigo;

    public Puesto(){}

    public Puesto(String codigo_puesto){
        this.codigo = codigo_puesto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getIdP() {
        return idP;
    }

    public void setIdP(Integer idP) {
        this.idP = idP;
    }
}
