package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.Paso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasoRepository extends JpaRepository<Paso,Long> {
}
