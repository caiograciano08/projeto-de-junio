package br.com.alunoonline.api.dtos;

import lombok.Data;

/**
 * Objeto de transferência utilizado para receber as notas na atualização de matrícula.
 */
@Data
public class AtualizarNotasRequestDTO {

    private Double nota1;
    private Double nota2;

}
