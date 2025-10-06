package fabrica.lineaDemo.Services;

import fabrica.lineaDemo.DTOS.InfoGeneralDTO;
import fabrica.lineaDemo.DTOS.ValeLectura;
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


    public String BuscarProducto (){
        OrdenProduccion orden =  ordenProdRepo.getReferenceById(1); // por ahora solo va a haber una orden.
        //la idea es que busque por dia (hoy).
        String producto = orden.getProducto().getCodigo();
        return producto;


    }

    public InfoGeneralDTO darInfoGeneral(){
        InfoGeneralDTO dto = new InfoGeneralDTO();
        //conseguimos el nro de OrdenProduccion (en este caso sera el 1 nomas)
        OrdenProduccion orden = ordenProdRepo.getReferenceById(1);
        //conseguimos el codigo de formula.
        String codigoFormula = orden.getFormula().getCodigoFormula();
        //el codigo del producto terminado.
        String codigoProducto = orden.getProducto().getCodigo();

        String codigoOrdenProduccion = String.format("%d-%s-%s",orden.getId(),codigoFormula,codigoProducto);

        //cargamos el serial (seria por ej: 1-NBK001-100_1ntk_1)
        dto.setOrdenProduccion(codigoOrdenProduccion);
        dto.setVersionFormula(orden.getFormula().getVersion());
        dto.setCodigoProducto(codigoProducto);

        return dto;
    }



}
