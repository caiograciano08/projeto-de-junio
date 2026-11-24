package br.com.alunoonline.api.infra.security;

import br.com.alunoonline.api.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro executado uma vez por requisição para validar o token JWT e autenticar o usuário no contexto.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    /**
     * Ignora o filtro JWT nas rotas públicas de autenticação, cadastro e documentação Swagger.
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String caminho = request.getServletPath();
        return caminho.equals("/login")
                || caminho.equals("/cadastros")
                || caminho.equals("/swagger-ui.html")
                || caminho.equals("/swagger-ui")
                || caminho.startsWith("/swagger-ui/")
                || caminho.equals("/v3/api-docs")
                || caminho.equals("/v3/api-docs.yaml")
                || caminho.startsWith("/v3/api-docs/");
    }

    /**
     * Intercepta a requisição HTTP, extrai e valida o token Bearer e registra o usuário autenticado.
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        var tokenExtraido = recuperarToken(request);

        if (tokenExtraido != null) {
            var loginUsuario = tokenService.getSubject(tokenExtraido);
            var usuarioEncontrado = repository.findByLogin(loginUsuario);

            var contextoAutenticacao = new UsernamePasswordAuthenticationToken(
                    usuarioEncontrado,
                    null,
                    usuarioEncontrado.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(contextoAutenticacao);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Extrai o token JWT do cabeçalho Authorization, removendo o prefixo "Bearer ".
     */
    private String recuperarToken(HttpServletRequest request) {
        var cabecalhoAuthorization = request.getHeader("Authorization");
        if (cabecalhoAuthorization != null) {
            return cabecalhoAuthorization.replace("Bearer ", "");
        }
        return null;
    }

}
