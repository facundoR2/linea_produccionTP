package cent_35.demo.Controllers;

import cent_35.demo.Dtos.PasoDTO;
import cent_35.demo.Dtos.TramiteDTO;
import cent_35.demo.Models.Paso;
import cent_35.demo.Models.Recinto;
import cent_35.demo.Models.Tramite;
import cent_35.demo.Models.TramitePaso;
import cent_35.demo.repositorys.PasoRepository;
import cent_35.demo.repositorys.RecintoRepository;
import cent_35.demo.repositorys.TramitePasoRepository;
import cent_35.demo.repositorys.TramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TramiteController {

    @Autowired
    private TramiteRepository tramiteRepository;

    @Autowired
    private RecintoRepository recintoRepository;

    @Autowired
    private TramitePasoRepository tramitePasoRepository;

    @Autowired
    private PasoRepository pasoRepository;

    @PostMapping("/tramite/crear")
    public ResponseEntity<Object> crearTramite(@RequestBody TramiteDTO tramiteDTO){

        System.out.println("Contenido del tramiteDTO: "+ tramiteDTO);

        if(tramiteDTO.getPasos() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los pasos no pueden estar vacios");
        }
        System.out.println("Pasos recibidos: "+ tramiteDTO.getPasos());


        if(tramiteDTO.getNombreRecinto().isBlank()){
            return new ResponseEntity<>("El nombre del recinto no puede estar vacio", HttpStatus.BAD_REQUEST);
        }else {
             Recinto recinto = recintoRepository.findBynombre(tramiteDTO.getNombreRecinto());
             if(recinto == null){
                 return new ResponseEntity<>("NO se encontró ningun recinto con el nombre proporcionado",HttpStatus.BAD_REQUEST);
             }
             try{
                 Tramite tramite = new Tramite(tramiteDTO.getNombre(),recinto);
                 int orden = 1; // marcamos el orden de los pasos

                 for(PasoDTO pasoDTO: tramiteDTO.getPasos()){
                     Paso paso = new Paso(pasoDTO.getNombre(),pasoDTO.getDescripcion());
                     pasoRepository.save(paso);
                     tramiteRepository.save(tramite);


                     TramitePaso tramitePasos = new TramitePaso(tramite,paso,orden++);
                     tramitePasoRepository.save(tramitePasos); //guardo relacion.

                 }

             }catch (Exception e){
                 e.printStackTrace();
                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al crear tramite" + e.getMessage());
             }

            return new ResponseEntity<>("Tramite creado correctamente",HttpStatus.OK);

        }
    }
}
