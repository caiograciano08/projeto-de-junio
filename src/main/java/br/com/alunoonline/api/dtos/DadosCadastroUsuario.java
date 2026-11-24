package br.com.alunoonline.api.dtos;

import jakarta.validation.constraints.NotBlank;

/**
 * Dados de entrada validados para o cadastro de um novo usuário.
 */
public record DadosCadastroUsuario(
        @NotBlank(message = "Login é Obrigatório!!!")
        String login,
        @NotBlank(message = "Senha é Obrigatório!!!")
        String senha
) {
}
