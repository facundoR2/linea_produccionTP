package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.ValeProduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValeProduccionRepository extends JpaRepository<ValeProduccion,Integer> {



    List<ValeProduccion>findByOrdenProduccion_IdOP(Integer IdOP);

    List<ValeProduccion>findByEstado(Integer estado);
}
