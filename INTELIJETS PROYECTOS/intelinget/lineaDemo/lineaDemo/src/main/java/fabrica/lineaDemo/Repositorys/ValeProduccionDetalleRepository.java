package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.ValeProduccionDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValeProduccionDetalleRepository extends JpaRepository<ValeProduccionDetalle,Long> {
}
