package br.com.alunoonline.api.infra.security;

/**
 * DTO de resposta contendo o token JWT emitido após autenticação bem-sucedida.
 */
public record DadosTokenJWT(String token) {
}
