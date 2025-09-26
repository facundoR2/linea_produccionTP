package cent_35.demo.repositorys;

import cent_35.demo.Models.Tramite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TramiteRepository extends JpaRepository<Tramite,Integer> {

    @Override
    Optional<Tramite> findById(Integer integer);

    Tramite findByNombre(String nombre);
}
