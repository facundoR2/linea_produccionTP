package fabrica.lineaDemo.Services;

import fabrica.lineaDemo.Models.Formula;
import fabrica.lineaDemo.Repositorys.FormulaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormulaService {

    private final FormulaRepository rep;

    public FormulaService(FormulaRepository rep)
    {this.rep = rep;}

    public List<Formula> listar(){
        return rep.findAll();
    }

    //buscar por codigoDetalle
    public Formula buscarPorCodigoFormula(String codformula){
         return rep.findByCodigoFormula(codformula);
    }
    public Formula guardarFormula(Formula f){
        return rep.save(f);
    }
    public Optional<Formula> obtenerPorId(Integer id){
        return rep.findById(id);
    }
}
