package fabrica.lineaDemo.Controllers;

import fabrica.lineaDemo.DTOS.ValeProduccionDTO;
import fabrica.lineaDemo.Services.ValeProduccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ValeProduccion")
public class ValeProduccionController {


    private final ValeProduccionService service;


    public ValeProduccionController(ValeProduccionService service){
        this.service = service;
    }



    //crear valeProduccion

    @PostMapping("/crear")
    public ResponseEntity<Object> crearVale(@RequestBody ValeProduccionDTO valeProduccionDTO){
        if(valeProduccionDTO.getCodigoProducto() == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El codigo del producto dentro del vale no existe");

        }


        return ResponseEntity.ok().body("Vale Registrado Correctamente");
    }

    @GetMapping("/{idVale}/validad-componente/{idComponente}")
    public ResponseEntity<Object>validarComponente(@PathVariable Integer idVale, @PathVariable Integer idComponente){
        //devuelve true si esta el componente en la formula, sino devuelve false.
        var existe = service.validarComponenteEnFormula(idVale,idComponente);
        if(!existe){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el componente no existe en la formula");
        }
        return ResponseEntity.ok().body("el componente es valido para la orden de produccion");
    }


    @GetMapping("/generar-reporte-vales/{idOP}")
    public ResponseEntity<Object> generarReporteVales(@PathVariable Integer idOP){
        return ResponseEntity.ok().body(service.listarPorOrden(idOP));
    }



}
