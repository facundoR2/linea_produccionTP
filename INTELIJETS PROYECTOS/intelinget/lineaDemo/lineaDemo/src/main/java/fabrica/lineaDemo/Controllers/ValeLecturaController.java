package fabrica.lineaDemo.Controllers;

import fabrica.lineaDemo.DTOS.ValeLectura;
import fabrica.lineaDemo.Services.ValeLecturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vales")
public class ValeLecturaController {

    private final ValeLecturaService service;

    public ValeLecturaController(ValeLecturaService service){
        this.service = service;
    }







}
