package br.com.alunoonline.api.infra.security;

import br.com.alunoonline.api.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Serviço responsável pela geração e validação de tokens JWT com assinatura HMAC-SHA256.
 */
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Gera um token JWT assinado contendo o login do usuário e prazo de expiração de 2 horas.
     */
    public String gerarToken(Usuario usuario) {
        try {
            var algoritmoHmac = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Aluno Online")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmoHmac);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token jwt ", exception);
        }
    }

    /**
     * Valida a assinatura e o emissor do token JWT recebido, retornando o login (subject).
     */
    public String getSubject(String tokenJWT) {
        try {
            var algoritmoHmac = Algorithm.HMAC256(secret);
            return JWT.require(algoritmoHmac)
                    .withIssuer("API Aluno Online")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    /**
     * Calcula o instante de expiração do token para 2 horas após o horário atual (fuso -03:00).
     */
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
