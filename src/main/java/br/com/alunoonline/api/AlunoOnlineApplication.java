package br.com.alunoonline.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal responsável por inicializar a aplicação Spring Boot Aluno Online.
 */
@SpringBootApplication
public class AlunoOnlineApplication {

    /**
     * Ponto de entrada da aplicação.
     *
     * @param args argumentos de linha de comando
     */
    public static void main(String[] args) {
        SpringApplication.run(AlunoOnlineApplication.class, args);
    }

}
