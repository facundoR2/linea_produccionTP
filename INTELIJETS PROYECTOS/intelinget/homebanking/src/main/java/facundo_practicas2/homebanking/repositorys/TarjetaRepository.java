package facundo_practicas2.homebanking.repositorys;

import facundo_practicas2.homebanking.models.Cuenta;
import facundo_practicas2.homebanking.models.Tarjeta;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarjetaRepository extends JpaRepository<Tarjeta,Integer> {

    List<Tarjeta> findByNumero(String numero);
}
