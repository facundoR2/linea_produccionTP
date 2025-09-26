package fabrica.lineaDemo.Controllers;

import fabrica.lineaDemo.DTOS.ProcesoDTO;
import fabrica.lineaDemo.Services.FormulaService;
import fabrica.lineaDemo.Services.ProcesoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proceso")
public class ProcesoController {



    private final ProcesoService service;

    public ProcesoController(ProcesoService service){
        this.service = service;
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> crearProceso(@RequestBody ProcesoDTO procesoDTO){

        System.out.println("contenido del procesoDTO:"+ procesoDTO);

        if(procesoDTO.getPasos() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("los pasos no pueden estar vacios");
        }
        System.out.println("pasos recibidos:"+procesoDTO.getPasos());

        if (procesoDTO.getNombre() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el nombre del proceso no puede estar vacio");
        }else {
            //encuentra el codigo de formula asociado.
            var codFormula = service.buscarFormulaAsociada(procesoDTO.getCodFormula());
            if (codFormula == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el proceso debe estar asociado a una Formula");
            }else {
                //si existe, ingresa en FormulaPuestos, que la formula que se hace en tal puesto tiene un nuevo ProcesosPasos

            }

        }
        return ResponseEntity.ok().body("Proceso agregado Existosamente") ;
    }
}
