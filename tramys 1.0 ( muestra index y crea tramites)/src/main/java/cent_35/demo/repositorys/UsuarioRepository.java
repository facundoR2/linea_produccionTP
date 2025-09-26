package cent_35.demo.repositorys;

import cent_35.demo.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    List<Usuario> findByApellido(String apellido);

    Usuario findById(int id);
    Usuario findByCorreo (String correo);
}
