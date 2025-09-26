package fabrica.lineaDemo.Controllers;

import fabrica.lineaDemo.Models.FormulaDetalle;
import fabrica.lineaDemo.Services.FormulDetalleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/formula-detalles")
public class FormulaDetalleController {

    private final FormulDetalleService service;


    public FormulaDetalleController(FormulDetalleService service){
        this.service = service;
    }


    @GetMapping("/formula/{codigo}")
    public List<FormulaDetalle> listarPorFormula(@PathVariable String codigo){
        return service.ListarPorFormula(codigo);
    }
}
