package br.edu.utfpr.cp.espjava.crud_cidades;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    //criando usuários programaticamente (método não mais necessário
    // uma vez que a criação dos usuários e senhas estão sendo feitas no banco pelo script data.sql)
   /*
    @Bean
   public InMemoryUserDetailsManager configure() throws Exception {
        UserDetails john = User.withUsername("john")
                                .password(cifrador().encode("test123"))
                                .roles("listar")
                                .build();

        UserDetails alana = User.withUsername("alana")
                                .password(cifrador().encode("test123"))
                                .roles("admin")
                                .build();
        
        return new InMemoryUserDetailsManager(john, alana);                                
    }
*/

    //criando autorização
    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                    auth -> {
                        auth.requestMatchers("/").hasAnyRole("listar", "admin");
                        auth.requestMatchers("/criar", "/alterar", "/preparaAlterar").hasRole("admin");
                        auth.requestMatchers("/mostrar").authenticated();
                        auth.anyRequest().denyAll();
                    }
                ).csrf(AbstractHttpConfigurer::disable)
                .formLogin(
                    form -> form.loginPage("/login.html").permitAll()
                )
                .logout(logout -> logout.permitAll())
                .build();
    }
    
    //código para cifrar senhas
    @Bean
    public PasswordEncoder cifrador() {
        return new BCryptPasswordEncoder();
    }

    //Registrando LogIn
    @EventListener(InteractiveAuthenticationSuccessEvent.class)
    public void printUsuarioAtual(InteractiveAuthenticationSuccessEvent event) {

        var usuario = event.getAuthentication().getName();

        System.out.println(usuario);
    }
}
