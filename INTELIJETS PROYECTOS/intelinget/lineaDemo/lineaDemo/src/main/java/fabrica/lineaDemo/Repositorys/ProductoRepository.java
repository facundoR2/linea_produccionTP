package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {


    Optional<Producto> findByCodigo(String codigo);

    List<Producto>findByNombreContainingIgnoreCase(String nombre);
}
