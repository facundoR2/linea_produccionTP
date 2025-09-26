package cent_35.demo.repositorys;

import cent_35.demo.Models.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento,Integer> {

    Documento findBynombre(String nombre);
}
