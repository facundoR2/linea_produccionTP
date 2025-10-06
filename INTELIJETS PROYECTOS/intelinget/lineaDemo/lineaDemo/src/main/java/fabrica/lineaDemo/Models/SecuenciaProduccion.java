package fabrica.lineaDemo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SecuenciaProduccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSP;

    private Integer ultimoValor;



    public SecuenciaProduccion(Integer ultimoValor){
        this.ultimoValor = ultimoValor;
    }

    public Integer getIdSP() {
        return idSP;
    }

    public void setIdSP(Integer idSP) {
        this.idSP = idSP;
    }

    public Integer getUltimoValor() {
        return ultimoValor;
    }

    public void setUltimoValor(Integer ultimoValor) {
        this.ultimoValor = ultimoValor;
    }
}
