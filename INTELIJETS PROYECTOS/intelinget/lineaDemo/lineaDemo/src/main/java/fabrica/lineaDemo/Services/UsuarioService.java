package fabrica.lineaDemo.Services;

import fabrica.lineaDemo.DTOS.UsuarioDTO;
import fabrica.lineaDemo.DTOS.UsuarioLoginDTO;
import fabrica.lineaDemo.Models.Puesto;
import fabrica.lineaDemo.Models.Usuario;
import fabrica.lineaDemo.Repositorys.PuestoRepository;
import fabrica.lineaDemo.Repositorys.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service

public class UsuarioService {

    private final UsuarioRepository userRepo;

    private final PuestoRepository puestoRepository;



    public UsuarioService( PuestoRepository puestoRepository,
                           UsuarioRepository userRepo){
        this.userRepo = userRepo;
        this.puestoRepository = puestoRepository;
    }





    public Boolean loginRequest(UsuarioLoginDTO userR){
        Usuario user = userRepo.findByEmailAndPassword(userR.getUsername(),userR.getPassword());
        if(user.getEmail().isEmpty() && user.getPassword().isEmpty()){

            return false;
        }
        return true;


    }
    public UsuarioDTO solicitarPuesto(UsuarioDTO userDTO,Integer idpuesto){
        //verificamos que el puesto existe.
        userDTO.setId(puestoRepository.findByIdP(idpuesto).getIdP());
        if (userDTO.getId() == null){

        }
        return userDTO;

    }









}
