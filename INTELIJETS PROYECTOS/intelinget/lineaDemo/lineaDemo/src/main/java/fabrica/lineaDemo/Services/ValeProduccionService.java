package fabrica.lineaDemo.Services;


import fabrica.lineaDemo.DTOS.ComponenteDTO;
//import fabrica.lineaDemo.DTOS.ProductoDTO;
import fabrica.lineaDemo.DTOS.ValeLectura;
import fabrica.lineaDemo.DTOS.ValeProduccionDTO;
import fabrica.lineaDemo.Models.*;
import fabrica.lineaDemo.Repositorys.FormulaDetalleRepository;
import fabrica.lineaDemo.Repositorys.OrdenProduccionRepository;
import fabrica.lineaDemo.Repositorys.UsuarioRepository;
import fabrica.lineaDemo.Repositorys.ValeProduccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValeProduccionService {

    private final ValeProduccionRepository valeProduccionrep;
    private final OrdenProduccionRepository ordenProduccionrep;

    private final FormulaDetalleRepository formulaDetallerep;

    private final UsuarioRepository usuarioRepository;


    public ValeProduccionService(UsuarioRepository usuarioRepository,ValeProduccionRepository valeProduccionrep,OrdenProduccionRepository ordenProduccionrep, FormulaDetalleRepository formulaDetallerep){
        this.valeProduccionrep = valeProduccionrep;
        this.ordenProduccionrep = ordenProduccionrep;
        this.formulaDetallerep = formulaDetallerep;
        this.usuarioRepository = usuarioRepository;

    }





    public List<ValeProduccion> listarPorOrden(Integer idOP){
        return valeProduccionrep.findByOrdenProduccion_IdOP(idOP);
    }
    public List<ValeProduccion> ListarPorEstado(Integer estado){
        return valeProduccionrep.findByEstado(estado);
    }
    public boolean validarComponenteEnFormula(Integer idValeProduccion, Integer idComponente){

        //valida que el valeProduccion (cabecera) existe.
        ValeProduccion vale = valeProduccionrep.findById(idValeProduccion).orElseThrow(()->new RuntimeException("Vale de Produccion no encontrado"));

        OrdenProduccion orden = ordenProduccionrep.findById(vale.getOrdenProduccion().getIdOP()).orElseThrow(() -> new RuntimeException("Orden de Produccion no encontrada"));

        String codigoFormula = orden.getFormula().getCodigoFormula();

        //extraemos los detalles de la formula
        List<FormulaDetalle> detalles = formulaDetallerep.findByFormula_codigoFormula(codigoFormula);

        //validar si el componente esta dentro de los detalles.
        return detalles.stream().anyMatch(fd-> fd.getComponente().getId_producto().equals(idComponente));
    }

    //proceso que devuelve un Boolean para confirmar o no la creacion de Vales
    public void RegistrarValeProduccion (ValeProduccionDTO valeProduccionDTO){

        if(valeProduccionDTO.getCodigoProducto() == null){
            throw new IllegalStateException("el codigo padre del vale no puede estar vacio");
        }
        for (ComponenteDTO cmp:valeProduccionDTO.getComponentes()) {
            if (cmp.getCodigoProducto() == null|| cmp.getNombre() == null){

            }

        }


    }


}
