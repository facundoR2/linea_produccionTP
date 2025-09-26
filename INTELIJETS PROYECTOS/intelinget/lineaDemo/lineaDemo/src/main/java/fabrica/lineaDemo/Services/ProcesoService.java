package fabrica.lineaDemo.Services;

import fabrica.lineaDemo.Models.Formula;
import fabrica.lineaDemo.Models.Proceso;
import fabrica.lineaDemo.Repositorys.FormulaRepository;
import fabrica.lineaDemo.Repositorys.ProcesoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesoService {

    private final ProcesoRepository rep;

    private final FormulaRepository Frep;

    public ProcesoService(ProcesoRepository rep, FormulaRepository Frep){
        this.rep = rep;
        this.Frep = Frep;
    }

    public List<Proceso> listar(){
        return rep.findAll();
    }

    public Proceso guardar(Proceso p){
        return rep.save(p);
    }

    public Formula buscarFormulaAsociada(String codFormula){
        return Frep.findByCodigoFormula(codFormula);
    }
}
