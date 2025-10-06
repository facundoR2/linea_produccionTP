package fabrica.lineaDemo.Configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@EnableWebSecurity
@Configuration
class Authorization{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // desactiva CSRF( para apis rest)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() //permite todo_Por_hora.
                )
                .httpBasic(basic ->{}); // habilita Basic Auth (aun que todo_se_permite)



        return http.build();
    }

   // private void clearAuthenticationAttributes(HttpServletRequest request) {

    //    HttpSession session = request.getSession(false);

      //  if (session != null) {

      //      session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    //
   //     }
   // }

}
