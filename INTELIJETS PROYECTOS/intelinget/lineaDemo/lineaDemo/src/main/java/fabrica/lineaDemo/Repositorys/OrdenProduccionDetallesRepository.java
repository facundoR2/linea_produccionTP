package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.OrdenProduccion_Detalles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenProduccionDetallesRepository extends JpaRepository<OrdenProduccion_Detalles,Integer> {
}
