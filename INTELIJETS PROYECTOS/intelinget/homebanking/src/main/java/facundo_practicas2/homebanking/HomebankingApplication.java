package facundo_practicas2.homebanking;

import facundo_practicas2.homebanking.models.*;
import facundo_practicas2.homebanking.repositorys.ClienteRepository;
import facundo_practicas2.homebanking.repositorys.CuentaRepository;
import facundo_practicas2.homebanking.repositorys.PrestamoRepository;
import facundo_practicas2.homebanking.repositorys.TarjetaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	//comentar bloque = ctrl+mayus+/

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}



	@Bean
	public CommandLineRunner initData(ClienteRepository clienteRepository, CuentaRepository cuentaRepository,
									  PrestamoRepository prestamoRepository, TarjetaRepository tarjetaRepository,PasswordEncoder passwordEncoder){
		return(args) -> {
			//generacion de cuentas.

			if(clienteRepository.findAll().isEmpty()){
				Cliente cliente = new Cliente("Miguel", "Rejas","rejas@gmail.com", passwordEncoder.encode("1234"));
				Cliente cliente1 = new Cliente("jose","perez","jperez@gmail.com","2233");
				Cliente cliente2 = new Cliente("ezequiel","rios","rrgmail.com","abc123");
				cliente.setRol(Roles.CLIENTE);

				clienteRepository.save(cliente);
				clienteRepository.save(cliente1);
				clienteRepository.save(cliente2);

				Cuenta cuenta0 = new Cuenta("01",100000.00);
				Cuenta cuenta1 = new Cuenta("02",3000.00);
				Cuenta cuenta2 = new Cuenta("03",4500.00);

				//adherimos la cuenta a los clientes.
				cliente.addCuenta(cuenta0);
				cliente1.addCuenta(cuenta1);
				cliente2.addCuenta(cuenta2);
				cuentaRepository.save(cuenta0);
				cuentaRepository.save(cuenta1);
				cuentaRepository.save(cuenta2);
				//agregamos tarjetas.
				Tarjeta tarjeta = new Tarjeta(cliente,"0000-0000-0001",5,TarjetaTipo.GOLD,TarjetaColor.DORADO,"345");
				Tarjeta tarjeta1 = new Tarjeta(cliente,"0000-0000-0002",5,TarjetaTipo.TITANIUM,TarjetaColor.TITANIO,"229");
				Tarjeta tarjeta2 = new Tarjeta(cliente1,"0000-0000-0003",5,TarjetaTipo.SILVER,TarjetaColor.PLATINO,"699");
				cliente1.addTarjeta(tarjeta2);
				cliente.addTarjeta(tarjeta1);
				cliente.addTarjeta(tarjeta);
				tarjetaRepository.save(tarjeta);
				tarjetaRepository.save(tarjeta1);
				tarjetaRepository.save(tarjeta2);


			}//generacion de prestamos.
			if(prestamoRepository.findAll().isEmpty()) {
				Prestamo prestamo1 = new Prestamo("Hipotecario", 50000, List.of(12, 24, 36, 48, 600));
				Prestamo prestamo2 = new Prestamo("Personal",100000,List.of(6,12,24));
				Prestamo prestamo3 = new Prestamo("Automotriz",300000,List.of(6,12,24,36));
			}


		};
	}
}



