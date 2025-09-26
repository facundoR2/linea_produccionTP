package cent_35.demo;

import cent_35.demo.Models.Rol;
import cent_35.demo.Models.Usuario;
import cent_35.demo.repositorys.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TramySApplication {

	public static void main(String[] args) {
		SpringApplication.run(TramySApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
		return (args)-> {
			//generacion de cuenta
			if(usuarioRepository.findAll().isEmpty()){
				Usuario usuario1 = new Usuario("facu","rios","rios@gmail.com", passwordEncoder.encode("1234"));
				Usuario usuario2 = new Usuario("juan","Perez","perez@gmail.com", passwordEncoder.encode("1111"));
				usuario1.setRol(Rol.ADMINISTRADOR);
				usuario2.setRol(Rol.USUARIO);

				usuarioRepository.save(usuario1);
				usuarioRepository.save(usuario2);
			}
		};
	}

}
