package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.ValeDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValeDetalleRepository extends JpaRepository<ValeDetalle,Long> {
}
