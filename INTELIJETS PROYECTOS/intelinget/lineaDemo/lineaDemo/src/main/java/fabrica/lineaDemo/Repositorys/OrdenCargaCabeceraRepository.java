package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.OrdenCargaCabecera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenCargaCabeceraRepository extends JpaRepository<OrdenCargaCabecera,Integer> {
}
