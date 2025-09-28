package fabrica.lineaDemo.Services;

import fabrica.lineaDemo.DTOS.ValeLectura;
import fabrica.lineaDemo.Repositorys.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional
public class ValeLecturaService {

    public final Map<String, ValeLectura> productosEnProceso = new ConcurrentHashMap<>();


    private final ValeProduccionDetalleRepository valeProduccionDetalleRepository;

    private final ProductoRepository componenteRepository;


    public ValeLecturaService(ProductoRepository componenteRepository, ValeProduccionDetalleRepository valeProduccionDetalleRepository){
        this.componenteRepository = componenteRepository;
        this.valeProduccionDetalleRepository = valeProduccionDetalleRepository;
    }



    public void procesarVale(ValeLectura valeLectura, int ultimoPuesto){
        String key = valeLectura.getIdTrazado();
        if (key == null || key.isBlank()){
            throw new IllegalArgumentException("Id de trazado es obligatorio para distinguir instancias en proceso");
        }

        productosEnProceso.compute(key,(k, enProceso) -> {
            if(enProceso == null) {
                //nuevo producto en proceso.
                return valeLectura;
            }else {
                //actualizar puesto y agregar componentes.
                enProceso.setPuestoActual(valeLectura.getPuestoActual());
                enProceso.getComponentes().addAll(valeLectura.getComponentes());
                if (valeLectura.getUsuario() != null) enProceso.setUsuario(valeLectura.getUsuario());
                return enProceso;
            }
        });


        ValeLectura enProceso = productosEnProceso.get(key);
        if (enProceso.getPuestoActual().equals(ultimoPuesto)){
            cerrarProducto(enProceso);
            productosEnProceso.remove(key);
        }

    }

    public void cerrarProducto(ValeLectura valeLectura){
        //guardar en table
    }







}
