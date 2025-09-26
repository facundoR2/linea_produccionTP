package cent_35.demo.repositorys;

import cent_35.demo.Models.Recinto;
import cent_35.demo.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface RecintoRepository extends JpaRepository<Recinto,Integer> {

    Recinto findBynombre(String nombre);

    @Override
    Optional<Recinto> findById(Integer integer);

    @Query("SELECT r FROM Recinto r ORDER BY FUNCTION('RAND')") //usamos funcion Rand de MYSQL
    List<Recinto> findRandomRecintos();

    List<Recinto> findBynombreContainingIgnoreCase(String nombre);

    @Modifying
    @Transactional
    @Query("UPDATE Recinto u SET u.activo = false WHERE u.id = :id")
    void desactivarRecinto(@Param("id") int id);
}
