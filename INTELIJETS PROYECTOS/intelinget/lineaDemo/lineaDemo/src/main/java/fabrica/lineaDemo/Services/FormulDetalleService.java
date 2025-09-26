package fabrica.lineaDemo.Services;


import fabrica.lineaDemo.Models.FormulaDetalle;
import fabrica.lineaDemo.Repositorys.FormulaDetalleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormulDetalleService {

    private final FormulaDetalleRepository rep;


    public FormulDetalleService(FormulaDetalleRepository rep){
        this.rep = rep;
    }

    public List<FormulaDetalle> ListarPorFormula(String codigoFormula){
        return rep.findByFormula_codigoFormula(codigoFormula);
    }


}
