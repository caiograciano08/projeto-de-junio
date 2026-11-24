package br.com.alunoonline.api.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Interceptador global de exceções para padronizar respostas de erro da API.
 */
@RestControllerAdvice
public class TratadorDeErros {

    /**
     * Trata falhas de validação de entrada (Bean Validation) retornando HTTP 400 com os campos inválidos.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var errosDeCampo = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(
                errosDeCampo.stream().map(DadosErroValidacao::new).toList()
        );
    }

    /**
     * Representação simplificada de um erro de validação por campo.
     */
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
