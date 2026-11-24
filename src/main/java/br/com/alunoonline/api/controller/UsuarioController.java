package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.DadosCadastroUsuario;
import br.com.alunoonline.api.dtos.DadosDetalhamentoUsuario;
import br.com.alunoonline.api.model.Usuario;
import br.com.alunoonline.api.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controlador responsável pelo cadastro de novos usuários na aplicação.
 */
@RestController
@RequestMapping("cadastros")
@SecurityRequirements
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra um novo usuário com senha criptografada, caso o login ainda não exista.
     */
    @PostMapping
    @Transactional
    public ResponseEntity cadastro(
            @RequestBody @Valid DadosCadastroUsuario dados,
            UriComponentsBuilder uriBuilder) {
        if (repository.findByLogin(dados.login()) != null) {
            return ResponseEntity.badRequest().body("Login já Cadastrado!!!!");
        }

        var senhaCodificada = passwordEncoder.encode(dados.senha());
        var novoUsuario = new Usuario(dados.login(), senhaCodificada);
        repository.save(novoUsuario);

        var recursoUri = uriBuilder
                .path("/cadastros/{id}")
                .buildAndExpand(novoUsuario.getId())
                .toUri();
        return ResponseEntity.created(recursoUri).body(new DadosDetalhamentoUsuario(novoUsuario));
    }

}
