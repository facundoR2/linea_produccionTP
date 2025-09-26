package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    List<Usuario> findByEmail(String email);

    Usuario findById(int id);
}
