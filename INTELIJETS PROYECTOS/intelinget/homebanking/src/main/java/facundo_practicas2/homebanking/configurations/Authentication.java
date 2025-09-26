package facundo_practicas2.homebanking.configurations;

import facundo_practicas2.homebanking.models.Cliente;
import facundo_practicas2.homebanking.models.Roles;
import facundo_practicas2.homebanking.repositorys.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class Authentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private ClienteRepository clienteRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName-> {
            Cliente cliente = clienteRepository.findByCorreo(inputName);
            if (cliente != null) {
                if(cliente.getRol().equals(Roles.CLIENTE)){
                    return new User(cliente.getCorreo(), cliente.getPassword(),
                            AuthorityUtils.createAuthorityList("CLIENTE"));
                }
                if(cliente.getRol().equals(Roles.ADMINISTRADOR)){
                    return new User(cliente.getCorreo(), cliente.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMINISTRADOR"));
                }
                throw new UsernameNotFoundException("Unknown user: " + inputName);

            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });
    }

}
