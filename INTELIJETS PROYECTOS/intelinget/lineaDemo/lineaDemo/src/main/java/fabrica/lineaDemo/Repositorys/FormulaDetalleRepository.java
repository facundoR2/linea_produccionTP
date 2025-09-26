package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.Formula;
import fabrica.lineaDemo.Models.FormulaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulaDetalleRepository extends JpaRepository<FormulaDetalle,Integer> {


    //buscar codigo de formula a traves de la relacion.
    List<FormulaDetalle> findByFormula_codigoFormula(String codigoFormula);

}
