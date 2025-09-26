package cent_35.demo.Controllers;

import cent_35.demo.Dtos.CrearUsuarioDTO;
import cent_35.demo.Dtos.DarBajaUsuarioDTO;
import cent_35.demo.Dtos.UsuarioDTO;
import cent_35.demo.Models.Rol;
import cent_35.demo.Models.Usuario;
import cent_35.demo.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // aca se derivan las peticiones
public class UsuarioController {

    @Autowired //para no instanciar a cada rato
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/usuario/current")
    public ResponseEntity<Object> getUsuarioActual() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Usuario usuario = usuarioRepository.findByCorreo(username);
            return new ResponseEntity<>(usuario,HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        }


    }

    @PostMapping("/usuario")
    public ResponseEntity<Object> crearUsuario(@RequestBody CrearUsuarioDTO crearUsuarioDTO){
        //crear validaciones
        if(crearUsuarioDTO.getNombre().isBlank() || crearUsuarioDTO.getApellido().isBlank() || crearUsuarioDTO.getCorreo().isBlank()){
            return new ResponseEntity<>("El campo no puede estar vacio. ", HttpStatus.FORBIDDEN);
        }
        Usuario usuario = new Usuario(crearUsuarioDTO.getNombre(),crearUsuarioDTO.getApellido(),crearUsuarioDTO.getCorreo(),passwordEncoder.encode(crearUsuarioDTO.getPassword()));
        usuario.setRol(Rol.USUARIO);
        usuarioRepository.save(usuario);

        return new ResponseEntity<>("USUARIO creado correctamente",HttpStatus.OK);
    }

    @PutMapping("/usuario/baja")
    public ResponseEntity<Object> bajarUsuario(@RequestBody DarBajaUsuarioDTO darBajaUsuarioDTO){
        if(darBajaUsuarioDTO.getNombre().isBlank()){
            return new ResponseEntity<>("el nombre de usuario no puede estar vacio",HttpStatus.BAD_REQUEST);

        }
        // creo un nuevo usuarioDTO y le coloco los datos del usuario buscado.
        Usuario usuario_baja = usuarioRepository.findByCorreo(darBajaUsuarioDTO.getCorreo()) ;
        usuario_baja.setEstado(0);
        try {
            usuarioRepository.save(usuario_baja);
        }catch (Exception e) {
            Exception falla_baja_repositorio = new Exception(e.getCause());
        }
        return new ResponseEntity<>("Usuario dado de baja correctamente",HttpStatus.OK);

    };



}

