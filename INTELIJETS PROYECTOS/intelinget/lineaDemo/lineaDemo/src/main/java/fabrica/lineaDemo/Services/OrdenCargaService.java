package fabrica.lineaDemo.Services;

import fabrica.lineaDemo.DTOS.OrdenProduccionRequest;
import fabrica.lineaDemo.Models.OrdenCarga;
import fabrica.lineaDemo.Models.OrdenCargaCabecera;
import fabrica.lineaDemo.Models.OrdenProduccionDetalles;
import fabrica.lineaDemo.Models.Producto;
import fabrica.lineaDemo.Repositorys.OrdenCargaCabeceraRepository;
import fabrica.lineaDemo.Repositorys.OrdenProduccionDetallesRepository;
import fabrica.lineaDemo.Repositorys.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenCargaService {

    private final ProductoRepository productoRepository;
    private final OrdenProduccionDetallesRepository detallesRepository;
    private final OrdenCargaCabeceraRepository cabecerarepo;


    public OrdenCargaService(ProductoRepository productoRepository, OrdenProduccionDetallesRepository detallesRepository, OrdenCargaCabeceraRepository cabecerarepo){
        this.productoRepository = productoRepository;
        this.detallesRepository = detallesRepository;
        this.cabecerarepo = cabecerarepo;
    }

    @Transactional
    public OrdenCargaCabecera procesarOrden(OrdenProduccionRequest request){
        //validar que la Orden de Produccion existe.
        OrdenProduccionDetalles detalle = detallesRepository.findById(request.getIdOrdenProduccion())
                .orElseThrow(() -> new IllegalArgumentException("orden de produccion no encontrada"));

        //generamos array con todos las validaciones necesarias
        List<OrdenCarga> validaciones = new ArrayList<>();

        //consigue toda la cantidad de componentes para la orden de Produccion
        for (OrdenProduccionRequest.ComponenteRequest cr : request.getComponentes()){
            Producto producto = productoRepository.findById(cr.getIdProducto())
                    .orElseThrow(() -> new IllegalArgumentException("producto no encontrado:" + cr.getIdProducto()));

            //generamos un proceso de Carga.
            OrdenCarga carga = new OrdenCarga(producto,cr.getCantNecesaria());


            carga.validarStock();




        }
        boolean ValidarRequest = validaciones.stream().allMatch(OrdenCarga::validarStock);
        if(!ValidarRequest){
            throw new IllegalStateException("la Hay suficiente Cantidad en Stock para cumplir con la Orden de Produccion");

        }
        OrdenCargaCabecera cabecera = new OrdenCargaCabecera();
        cabecera.setFormula(detalle.getFormula());
        cabecera.setOrdenProduccionDetalles(detalle);
        cabecera.setEstado("en produccion");
        //guardamos la Orden de Carga Valida en el Repositorio.
        return cabecerarepo.save(cabecera);
    }


}
