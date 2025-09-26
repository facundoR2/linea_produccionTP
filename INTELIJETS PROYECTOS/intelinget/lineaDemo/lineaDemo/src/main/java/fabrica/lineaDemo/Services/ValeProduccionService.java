package fabrica.lineaDemo.Services;


import fabrica.lineaDemo.Models.FormulaDetalle;
import fabrica.lineaDemo.Models.OrdenProduccion;
import fabrica.lineaDemo.Models.ValeProduccion;
import fabrica.lineaDemo.Repositorys.FormulaDetalleRepository;
import fabrica.lineaDemo.Repositorys.OrdenProduccionRepository;
import fabrica.lineaDemo.Repositorys.ValeProduccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValeProduccionService {

    private final ValeProduccionRepository valeProduccionrep;
    private final OrdenProduccionRepository ordenProduccionrep;

    private final FormulaDetalleRepository formulaDetallerep;


    public ValeProduccionService(ValeProduccionRepository valeProduccionrep,OrdenProduccionRepository ordenProduccionrep, FormulaDetalleRepository formulaDetallerep){
        this.valeProduccionrep = valeProduccionrep;
        this.ordenProduccionrep = ordenProduccionrep;
        this.formulaDetallerep = formulaDetallerep;

    }

    public List<ValeProduccion> listarPorOrden(Integer idOP){
        return valeProduccionrep.findByOrdenProduccion_IdOP(idOP);
    }
    public List<ValeProduccion> ListarPorEstado(Integer estado){
        return valeProduccionrep.findByEstado(estado);
    }
    public boolean validarComponenteEnFormula(Integer idValeProduccion, Integer idComponente){
        ValeProduccion vale = valeProduccionrep.findById(idValeProduccion).orElseThrow(()->new RuntimeException("Vale de Produccion no encontrado"));

        OrdenProduccion orden = ordenProduccionrep.findById(vale.getOrdenProduccion().getIdOP()).orElseThrow(() -> new RuntimeException("Orden de Produccion no encontrada"));

        String codigoFormula = orden.getFormula().getCodigoFormula();

        //extraemos los detalles de la formula
        List<FormulaDetalle> detalles = formulaDetallerep.findByFormula_codigoFormula(codigoFormula);

        //validar si el componente esta dentro de los detalles.
        return detalles.stream().anyMatch(fd-> fd.getComponente().getId_producto().equals(idComponente));
    }
}
