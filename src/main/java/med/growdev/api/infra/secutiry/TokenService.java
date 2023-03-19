package med.growdev.api.infra.secutiry;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.growdev.api.domain.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

//gera, valida os tokens
@Service
public class TokenService {
    public String gerarToken(User user){
        //copiei e colei da documentacao https://github.com/auth0/java-jwt
        try {
            //
            Algorithm algoritmo = Algorithm.HMAC256("senhaSecreta");
            String token = JWT.create()
                    .withIssuer("API grow")//quem é api que gera o token
                    .withSubject(user.getLogin()) //quem é relacionado com o token
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo); //aqui eu passo a assinatura
            return token;
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
