package br.com.alunoonline.api.dtos;

import br.com.alunoonline.api.model.Usuario;

/**
 * Dados públicos retornados após o cadastro ou detalhamento de um usuário.
 */
public record DadosDetalhamentoUsuario(Long id, String login) {

    /**
     * Constrói o DTO a partir da entidade {@link Usuario}, omitindo a senha.
     */
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin());
    }

}
