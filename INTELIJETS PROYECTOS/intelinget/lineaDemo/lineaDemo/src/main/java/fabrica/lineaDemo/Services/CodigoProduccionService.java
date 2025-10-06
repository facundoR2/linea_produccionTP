package fabrica.lineaDemo.Services;

import fabrica.lineaDemo.Models.SecuenciaProduccion;
import fabrica.lineaDemo.Repositorys.SecuenciaProduccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CodigoProduccionService {

    private final SecuenciaProduccionRepository repo;


    public CodigoProduccionService(SecuenciaProduccionRepository repo){
        this.repo = repo;
    }


    @Transactional
    public String generarCodigo(String codigoProducto){
        //traer el ultimo registro ( o generar uno si no existe)

        SecuenciaProduccion secuencia = repo.findById(1).orElseGet(() -> {
            SecuenciaProduccion s = new SecuenciaProduccion(0);
            s = repo.save(s);
            return s;
        });

        //incrementar contador
        int siguiente = secuencia.getUltimoValor()+1;
        secuencia.setUltimoValor(siguiente);
        repo.save(secuencia);

        //le damos formato al codigo
        String fecha = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        return String.format("%s-%s-%04d",codigoProducto,fecha,siguiente);
    }
}
