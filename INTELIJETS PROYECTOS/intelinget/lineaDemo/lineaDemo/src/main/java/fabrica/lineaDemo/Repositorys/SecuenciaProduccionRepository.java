package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.SecuenciaProduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecuenciaProduccionRepository extends JpaRepository<SecuenciaProduccion,Integer> {
}
