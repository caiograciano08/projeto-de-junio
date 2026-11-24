package br.com.alunoonline.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do SpringDoc/OpenAPI para habilitar autenticação Bearer JWT na interface do Swagger.
 */
@Configuration
public class OpenAPIConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    /**
     * Configura o OpenAPI com o esquema de segurança JWT global e metadados da API.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(
                                SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        ))
                .info(new Info()
                        .title("Aluno Online API")
                        .version("1.0")
                        .description(
                                "API Rest da aplicação Aluno Online, contendo as funcionalidades de CRUD "
                                        + "de alunos, professores e disciplinas, além de matrícula, trancamento "
                                        + "e lançamento de notas, protegida com Spring Security e JWT"
                        )
                        .contact(new Contact()
                                .name("Rodolfo Santiago Romero Garcia")));
    }

}
