package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.FormulaPuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulaPuestosRepository extends JpaRepository<FormulaPuesto,Integer> {

    //encontrar registros de un puesto especifico.
    List<FormulaPuesto> findByPuestoIdP(Integer idP);

    //buscar registros de un componente especifico
    List<FormulaPuesto> findByFormulaDetalleIdFD(Integer idFD);

    //buscar por un id de ProcesoPaso
    List<FormulaPuesto> findByProcesoPasoIdPP(Long idPP);

}
