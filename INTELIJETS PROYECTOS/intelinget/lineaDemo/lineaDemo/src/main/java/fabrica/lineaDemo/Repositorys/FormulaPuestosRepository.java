package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.FormulaPuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormulaPuestosRepository extends JpaRepository<FormulaPuesto,Integer> {


    //buscar todos los registros que tengan esta formula.

    List<FormulaPuesto>findByFormulaDetalle_Formula_CodigoFormula(String codigoFormula);




    //buscar todos los registros que tengan esta formula y este puesto.


    List<FormulaPuesto> findByFormulaDetalle_Formula_CodigoFormulaAndPuesto_IdP(String codigoFormula, Integer idPuesto);


    //buscar por codigo de formula y orden.
    Optional<FormulaPuesto> findByFormulaDetalle_Formula_CodigoFormulaAndOrden(String codigoFormula, Integer orden);

    //encontrar registros de un puesto especifico.
    List<FormulaPuesto> findByPuestoIdP(Integer idP);

    //buscar registros de un componente especifico
    List<FormulaPuesto> findByFormulaDetalleIdFD(Integer idFD);

    //buscar por un id de ProcesoPaso
    List<FormulaPuesto> findByProcesoPasoIdPP(Long idPP);

}
