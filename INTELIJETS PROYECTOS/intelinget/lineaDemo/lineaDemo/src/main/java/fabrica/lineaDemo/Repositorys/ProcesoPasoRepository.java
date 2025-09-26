package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.ProcesoPaso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcesoPasoRepository extends JpaRepository<ProcesoPaso,Long> {
    List<ProcesoPaso> findByProceso_IdProceso(Long idProceso);
}
