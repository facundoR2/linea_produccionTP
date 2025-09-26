package facundo_practicas2.homebanking.Controllers;


import facundo_practicas2.homebanking.dtos.ClienteDTO;
import facundo_practicas2.homebanking.dtos.CuentasDTO;
import facundo_practicas2.homebanking.dtos.TransaccionDTO;
import facundo_practicas2.homebanking.repositorys.ClienteRepository;
import facundo_practicas2.homebanking.repositorys.CuentaRepository;
import facundo_practicas2.homebanking.repositorys.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") // aqui se derivan las peticiones
public class CuentaController {

    @Autowired // para no instanciar a cada rato
    private CuentaRepository cuentaRepository;

    @GetMapping ("/cuentas")// deja en claro que esta peticion tiene el metodo GET
    public List<CuentasDTO> getCuentas(){
        return cuentaRepository.findAll().stream().map(Cuenta -> new CuentasDTO(Cuenta)).collect(Collectors.toList()); // busca todos los registro las tablas a cargo de cuentas.


    }
}
