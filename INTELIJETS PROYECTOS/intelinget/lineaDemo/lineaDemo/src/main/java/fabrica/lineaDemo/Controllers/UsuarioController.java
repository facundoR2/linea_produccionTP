package fabrica.lineaDemo.Controllers;

import fabrica.lineaDemo.DTOS.UsuarioDTO;
import fabrica.lineaDemo.DTOS.UsuarioLoginDTO;
import fabrica.lineaDemo.Services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {


    private final UsuarioService userService;


    public UsuarioController(
            UsuarioService userService
    ){
        this.userService = userService;


    }


    //ENDPOINT PARA VALIDACION DE USUARIO.

    @GetMapping("/login")
    public ResponseEntity<?>loginRequest(@RequestBody UsuarioLoginDTO user){

        if(userService.loginRequest(user) == false){
            throw new IllegalArgumentException("No se ha encontrado un usuario con este username o contraseña");
        }else {
            return ResponseEntity.ok("usuario ingresado correctamente");
        }

    }
    @PostMapping("/login_puesto")
    public ResponseEntity<UsuarioDTO> puestoRequest(UsuarioDTO usuario,Integer puestoId){
        UsuarioDTO operario = userService.solicitarPuesto(usuario,puestoId);

        return ResponseEntity.status(HttpStatus.OK).body(operario);
    }


}
