package br.com.alunoonline.api.repository;

import br.com.alunoonline.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Repositório Spring Data JPA para consulta e persistência de usuários de acesso.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Localiza um usuário pelo seu login de autenticação.
     */
    UserDetails findByLogin(String login);

}
