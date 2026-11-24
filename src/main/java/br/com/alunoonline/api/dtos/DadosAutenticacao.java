package br.com.alunoonline.api.dtos;

/**
 * Dados de entrada utilizados na requisição de autenticação (login).
 */
public record DadosAutenticacao(String login, String senha) {
}
