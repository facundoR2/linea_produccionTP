package cent_35.demo.repositorys;

import cent_35.demo.Dtos.TramiteDTO;
import cent_35.demo.Models.Tramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TramiteRepository extends JpaRepository<Tramite,Integer> {

    @Override
    Optional<Tramite> findById(Integer integer);

    Tramite findByNombre(String nombre);




    List<Tramite> findByRecintoNombre(String nombreRecinto);
}
