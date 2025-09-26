package cent_35.demo.repositorys;

import cent_35.demo.Models.Recinto;
import cent_35.demo.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecintoRepository extends JpaRepository<Recinto,Integer> {

    Recinto findBynombre(String nombre);

    @Override
    Optional<Recinto> findById(Integer integer);

    @Query("SELECT r FROM Recinto r ORDER BY FUNCTION('RAND')") //usamos funcion Rand de MYSQL
    List<Recinto> findRandomRecintos();

    List<Recinto> findBynombreContainingIgnoreCase(String nombre);
}
