package facundo_practicas2.homebanking.repositorys;

import ch.qos.logback.core.net.server.Client;
import facundo_practicas2.homebanking.models.Cliente;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    List<Cliente> findByApellido(String apellido);
    Cliente findById(int id);
    Cliente findByCorreo (String correo);
}
