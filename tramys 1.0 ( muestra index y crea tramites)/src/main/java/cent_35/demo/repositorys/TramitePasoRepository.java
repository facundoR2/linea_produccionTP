package cent_35.demo.repositorys;

import cent_35.demo.Models.Recinto;
import cent_35.demo.Models.Tramite;
import cent_35.demo.Models.TramitePaso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TramitePasoRepository extends JpaRepository<TramitePaso, Integer> {



    @Override
    Optional<TramitePaso> findById(Integer integer);

    List<TramitePaso> findByTramiteOrderByOrdenAsc(Tramite tramite);

}
