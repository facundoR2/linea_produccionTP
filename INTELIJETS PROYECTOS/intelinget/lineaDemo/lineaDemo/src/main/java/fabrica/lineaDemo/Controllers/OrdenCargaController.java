package fabrica.lineaDemo.Controllers;

import fabrica.lineaDemo.DTOS.OrdenProduccionRequest;
import fabrica.lineaDemo.Models.OrdenCargaCabecera;
import fabrica.lineaDemo.Models.OrdenProduccion;
import fabrica.lineaDemo.Services.OrdenCargaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/OrdenCarga")
public class OrdenCargaController {

    private final OrdenCargaService service;

    public OrdenCargaController(OrdenCargaService service) {
        this.service = service;
    }

    @PostMapping("/procesar")
    public OrdenCargaCabecera procesarOrden(@RequestBody OrdenProduccionRequest request) {
        return service.procesarOrden(request);
    }
}
