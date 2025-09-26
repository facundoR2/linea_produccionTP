package facundo_practicas2.homebanking.Controllers;

import facundo_practicas2.homebanking.dtos.ClienteDTO;
import facundo_practicas2.homebanking.dtos.CrearClienteDTO;
import facundo_practicas2.homebanking.models.Cliente;
import facundo_practicas2.homebanking.repositorys.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import facundo_practicas2.homebanking.models.Cliente;
import facundo_practicas2.homebanking.models.Roles;
import facundo_practicas2.homebanking.repositorys.ClienteRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") // aqui se derivan las peticiones
public class ClienteController {


    @Autowired // para no instanciar a cada rato
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping ("/clientes")// deja en claro que esta peticion tiene el metodo GET
    public List<ClienteDTO> getClientes(){
        // busca todos los registro las tablas a cargo de cliente.
        return clienteRepository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());

    }
    @GetMapping("/clientes/{id}")
    public ClienteDTO getCliente(@PathVariable int id){
        // creamos un clienteDTO que genera un cliente y usa el metodo para buscar por id.
        return new ClienteDTO(clienteRepository.findById(id));
    }
    @GetMapping("/clientes/current")
    public ResponseEntity<Object> getClienteActual(Authentication authentication){
        Cliente cliente = clienteRepository.findByCorreo(authentication.getName());
        return new ResponseEntity<>(new ClienteDTO(cliente), HttpStatus.OK);
    }

    @PostMapping("/cliente")
    public ResponseEntity<Object> crearCliente(@RequestBody CrearClienteDTO crearClienteDTO){
        //crear validaciones
        if(crearClienteDTO.getNombre().isBlank()){
            return new ResponseEntity<>("El campo no puede estar vacio.",HttpStatus.FORBIDDEN);
        }
        Cliente cliente = new Cliente(crearClienteDTO.getNombre(),crearClienteDTO.getApellido(),crearClienteDTO.getCorreo(),passwordEncoder.encode(crearClienteDTO.getPassword()));
        cliente.setRol(Roles.CLIENTE);
        clienteRepository.save(cliente);

        return new ResponseEntity<>("USUARIO creado correctamente", HttpStatus.OK);

    }

}
