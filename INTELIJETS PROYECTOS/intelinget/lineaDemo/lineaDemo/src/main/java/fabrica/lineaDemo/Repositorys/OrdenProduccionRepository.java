package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.OrdenProduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenProduccionRepository extends JpaRepository<OrdenProduccion,Integer> {
}
