package med.growdev.api.infra.secutiry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    //Configuração do processo de autenticacao e autorização
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .sessionManagement() //como será o gerenciamento de sessao
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //como será a polticia da sessão (será sem estado)
                .and().build();
    }//Agora o spring não terá o comportamento padrão, ou seja, nao terá mais a tela, e tbm nao vai bloquear as requisições, agora vamos ter que configurar isso

    //Depois de inserir o AuthenticationManager no Controller
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager(); //sabe criar o authenticationManager
    }
    //retorna a senha criptografada
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
