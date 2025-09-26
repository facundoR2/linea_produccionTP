package facundo_practicas2.homebanking.repositorys;

import facundo_practicas2.homebanking.models.Prestamo;
import facundo_practicas2.homebanking.models.Transaccion;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    List<Prestamo> findById(int id);
}
