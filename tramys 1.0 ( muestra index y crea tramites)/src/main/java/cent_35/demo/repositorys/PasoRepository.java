package cent_35.demo.repositorys;

import cent_35.demo.Models.Paso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasoRepository extends JpaRepository<Paso,Integer> {

    Paso findBynombre (String nombre);
}
