package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulaRepository extends JpaRepository<Formula,Integer> {

    Formula findByCodigoFormula(String codigoFormula);
}
