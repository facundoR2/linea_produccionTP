package cent_35.demo.services;

import cent_35.demo.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    //public void borrarLogicamente(int id){usuarioRepository.desactivarUsuario(id);}
}
