package facundo_practicas2.homebanking.repositorys;

import facundo_practicas2.homebanking.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryRestResource

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    List<Cuenta> findByNumero(int numero);

}
