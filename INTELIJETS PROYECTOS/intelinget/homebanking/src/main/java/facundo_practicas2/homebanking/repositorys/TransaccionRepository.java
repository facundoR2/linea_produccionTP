package facundo_practicas2.homebanking.repositorys;

import facundo_practicas2.homebanking.models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion,Integer> {

    List<Transaccion> findById(int id);





}
