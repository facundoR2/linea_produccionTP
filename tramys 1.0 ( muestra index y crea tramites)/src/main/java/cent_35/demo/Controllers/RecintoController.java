package cent_35.demo.Controllers;

import cent_35.demo.Dtos.BusquedaRecintoDTO;
import cent_35.demo.Dtos.CrearRecintoDTO;
import cent_35.demo.Dtos.ModRecintoDTO;
import cent_35.demo.Dtos.RecintoDTO;
import cent_35.demo.Models.Recinto;
import cent_35.demo.Models.Tramite;
import cent_35.demo.repositorys.RecintoRepository;
import cent_35.demo.services.RecintoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/recinto")
public class RecintoController {

    @Autowired
    private RecintoRepository recintoRepository;

    @Autowired
    private RecintoService recintoService;

//    @PostMapping("/guardar-coordenadas")
//    public ResponseEntity<String> guardarCoordenadas(@RequestBody CoordenadasDTO coordenadasDTO){
//        //crear un recinto
//        Recinto recinto = new Recinto();
//        recinto.setLat(coordenadasDTO.getLatitud());
//        recinto.setLng(coordenadasDTO.getLongitud());
//
//        //guardo en repositorio
//        recintoRepository.save(recinto);
//        return ResponseEntity.ok("Las coordenadas se guardaron correctamente");
//    }
    @GetMapping("/buscar")
    public List<BusquedaRecintoDTO> buscarRecinto(@RequestParam String nombre){
        return recintoService.buscarRecintosPorNombre(nombre);
    }







    @PostMapping("/nuevoRecinto")
    public ResponseEntity<Object> crearRecinto(@RequestBody CrearRecintoDTO crearRecintoDTO){
        if(Double.compare(crearRecintoDTO.getLat(), 0.0) == 0 || Double.compare(crearRecintoDTO.getLng(),0.0) == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("los campos 'lat' y 'lng' no pueden ser vacios");
        }

        if(crearRecintoDTO.getNombre().isBlank()){
            return new ResponseEntity<>("El campo nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
        }
        Recinto recinto = new Recinto(crearRecintoDTO.getLat(),crearRecintoDTO.getLng(),crearRecintoDTO.getNombre(),crearRecintoDTO.getDireccion(),crearRecintoDTO.gethAtencion(),crearRecintoDTO.gethCierre());
        recintoRepository.save(recinto);


        return new ResponseEntity<>("RECINTO creado correctamente",HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("los campos 'lat' y 'lng' no pueden ser nulos");
    }

    @GetMapping("/traerTanda")
    public List<RecintoDTO> getRecintosTanda(){
        return recintoService.obtenerRecintosAleatorios(5).stream()
                .map(recinto -> new RecintoDTO(
                        recinto.getLat(),
                        recinto.getLng(),
                        recinto.getNombre(),
                        recinto.getDireccion(),
                        recinto.gethAtencion(),
                        recinto.gethCierre()
                ))
                .collect(Collectors.toList());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarRecinto(@RequestBody ModRecintoDTO modRecintoDTO){
        try{
            Recinto recintoExistente = recintoService.buscarRecintoPorNombre(modRecintoDTO.getNombreAnteriorRecinto());

            if(recintoExistente !=null) {//verificamos que exista un recinto con ese nombre en la DB.
                recintoExistente.setLat(modRecintoDTO.getLat());
                recintoExistente.setLng(modRecintoDTO.getLng());
                recintoExistente.setNombre(modRecintoDTO.getNombreNuevoRecinto());
                recintoExistente.setDireccion(modRecintoDTO.getDireccion());
                recintoExistente.sethAtencion(modRecintoDTO.gethAtencion());
                recintoExistente.sethCierre(modRecintoDTO.gethCierre());

                recintoRepository.save(recintoExistente);
                return ResponseEntity.ok("Recinto Actualizado correctamente");
            }else {
                return  ResponseEntity.status(404).body("Recinto no encontrado");
            }
        } catch (Exception e){
            return ResponseEntity.status(500).body("ERROR al actualizar el recinto");
        }
    }






}
