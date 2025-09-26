package cent_35.demo.Controllers;

import cent_35.demo.Dtos.BusquedaRecintoDTO;
import cent_35.demo.Dtos.CrearRecintoDTO;
import cent_35.demo.Dtos.RecintoDTO;
import cent_35.demo.Models.Recinto;
import cent_35.demo.Models.Usuario;
import cent_35.demo.repositorys.RecintoRepository;
import cent_35.demo.repositorys.UsuarioRepository;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    //metodo para borrado logico.

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarRecinto(@PathVariable int id, String usuario){

        Usuario user = usuarioRepository.findByCorreo(usuario).orElse(null);
        recintoService.borrarRecintoLogicamente(id,usuario);
        return ResponseEntity.ok("Recinto desactivado");
    }

}
