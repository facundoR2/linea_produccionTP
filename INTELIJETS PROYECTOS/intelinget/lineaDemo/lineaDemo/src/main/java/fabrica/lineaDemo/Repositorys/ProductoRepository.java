package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    List<Producto>findByNombreContainingIgnoreCase(String nombre);
}
