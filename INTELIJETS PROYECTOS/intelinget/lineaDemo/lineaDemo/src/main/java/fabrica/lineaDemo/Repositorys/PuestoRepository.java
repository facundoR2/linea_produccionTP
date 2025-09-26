package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuestoRepository extends JpaRepository<Puesto,Long> {
}
