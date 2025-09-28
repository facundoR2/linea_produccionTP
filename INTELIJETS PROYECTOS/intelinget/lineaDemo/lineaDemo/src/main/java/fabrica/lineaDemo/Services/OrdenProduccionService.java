package fabrica.lineaDemo.Services;

import fabrica.lineaDemo.Models.OrdenProduccion;
import fabrica.lineaDemo.Models.ValeProduccionDetalle;
import fabrica.lineaDemo.Repositorys.OrdenProduccionRepository;
import fabrica.lineaDemo.Repositorys.ValeProduccionDetalleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenProduccionService {
    private final OrdenProduccionRepository ordenProdRepo;
    private final ValeProduccionDetalleRepository valeDetalleRepo;



    public OrdenProduccionService(OrdenProduccionRepository ordenProdRepo, ValeProduccionDetalleRepository valeDetalleRepo){
        this.ordenProdRepo = ordenProdRepo;
        this.valeDetalleRepo = valeDetalleRepo;

    }


    public Boolean crearOrden(OrdenProduccion ordenProduccion){
        //Validamos la Orden y la generamos.
        OrdenProduccion orden = ordenProdRepo.findById(ordenProduccion.getIdOP()).orElseThrow(()->new RuntimeException("Orden de Produccion No encontrada."));

        //en base a la cantidad, generamos los vales de produccion y los registramos.
        var cant = orden.getCantidad();
        List<ValeProduccionDetalle> lote = null;
        var count = 0;
        while (count<cant){
            ValeProduccionDetalle vale = new ValeProduccionDetalle();
            vale.setCodigoProducto(orden.getProducto().getCodigo());
            valeDetalleRepo.save(vale);
            count++;

        }

        return true;


    }

}
