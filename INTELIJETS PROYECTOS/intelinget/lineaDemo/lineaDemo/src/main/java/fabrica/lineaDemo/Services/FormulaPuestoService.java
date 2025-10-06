package fabrica.lineaDemo.Services;

import fabrica.lineaDemo.DTOS.UsuarioDTO;
import fabrica.lineaDemo.Models.FormulaPuesto;
import fabrica.lineaDemo.Repositorys.FormulaPuestosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormulaPuestoService {


    private final FormulaPuestosRepository rep;


    public FormulaPuestoService(FormulaPuestosRepository rep){
        this.rep = rep;
    }

    public List<FormulaPuesto> listarPorDetalle(Integer idFD){
        return rep.findByFormulaDetalleIdFD(idFD);
    }
    public  List<FormulaPuesto> listarPorPuesto(Integer idPuesto){
        return rep.findByPuestoIdP(idPuesto);
    }




}
