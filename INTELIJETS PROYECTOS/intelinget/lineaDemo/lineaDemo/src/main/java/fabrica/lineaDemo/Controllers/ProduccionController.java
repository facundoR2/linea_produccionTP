package fabrica.lineaDemo.Controllers;

import fabrica.lineaDemo.Services.ProduccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produccion")
public class ProduccionController {

    private final ProduccionService produccionService;

    public ProduccionController(ProduccionService produccionService){
        this.produccionService = produccionService;
    }


    @PostMapping("/cerrar/{idValeProduccion}")
    public ResponseEntity<String> cerrarProduccion(@PathVariable Integer idValeProduccion){
        produccionService.cerrarProduccion(idValeProduccion);
        return ResponseEntity.ok("Produccion cerrada correctamente y stock actualizado");
    }
}
