package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.OrdenProduccionDetalles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenProduccionDetallesRepository extends JpaRepository<OrdenProduccionDetalles,Integer> {
}
