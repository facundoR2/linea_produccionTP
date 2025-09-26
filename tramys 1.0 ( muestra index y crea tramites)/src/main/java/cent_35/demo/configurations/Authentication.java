package cent_35.demo.configurations;

import cent_35.demo.Models.Rol;
import cent_35.demo.Models.Usuario;
import cent_35.demo.repositorys.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName-> {
            Usuario usuario = usuarioRepository.findByCorreo(inputName);
            if (usuario != null) {
                if(usuario.getRol().equals(Rol.USUARIO)){
                    return new User(usuario.getCorreo(), usuario.getPassword(),
                            AuthorityUtils.createAuthorityList("USUARIO"));
                }
                if(usuario.getRol().equals(Rol.ADMINISTRADOR)){
                    return new User(usuario.getCorreo(), usuario.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMINISTRADOR"));
                }
                if(usuario.getRol().equals(Rol.CREADOR)){
                    return new User(usuario.getCorreo(), usuario.getPassword(),
                            AuthorityUtils.createAuthorityList("CREADOR"));
                }
                throw new UsernameNotFoundException("Unknown user: " + inputName);

            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });
    }

}
