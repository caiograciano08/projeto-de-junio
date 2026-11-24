package br.com.alunoonline.api.service;

import br.com.alunoonline.api.MatriculaAlunoStatusEnum;
import br.com.alunoonline.api.dtos.AtualizarNotasRequestDTO;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Camada de serviço responsável pelas regras de matrícula, lançamento de notas e trancamento.
 */
@Service
public class MatriculaAlunoService {

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    private static final Double MEDIA_PARA_APROVACAO = 7.0;

    /**
     * Atualiza parcialmente as notas de uma matrícula e define aprovação ou reprovação quando ambas estiverem preenchidas.
     */
    public void atualizarNotas(
            Long id,
            AtualizarNotasRequestDTO dto) {
        // Localiza a matrícula pelo ID ou retorna HTTP 404 caso não exista
        MatriculaAluno matriculaEncontrada = matriculaAlunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Matricula não encontrada"
                ));

        // Atualiza apenas as notas enviadas na requisição (comportamento de PATCH parcial)
        if (dto.getNota1() != null) {
            matriculaEncontrada.setNota1(dto.getNota1());
        }
        if (dto.getNota2() != null) {
            matriculaEncontrada.setNota2(dto.getNota2());
        }

        // Calcula a média e atualiza o status assim que as duas notas estiverem registradas
        if (matriculaEncontrada.getNota1() != null
                && matriculaEncontrada.getNota2() != null) {
            Double mediaNotas = (matriculaEncontrada.getNota1()
                    + matriculaEncontrada.getNota2()) / 2;
            matriculaEncontrada.setStatus(
                    mediaNotas >= MEDIA_PARA_APROVACAO
                            ? MatriculaAlunoStatusEnum.APROVADO
                            : MatriculaAlunoStatusEnum.REPROVADO
            );
        }

        // Persiste as alterações da matrícula no banco de dados
        matriculaAlunoRepository.save(matriculaEncontrada);
    }

    /**
     * Registra uma nova matrícula iniciando sempre com o status MATRICULADO.
     */
    public void criarMatricula(MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    /**
     * Altera o status da matrícula para TRANCADO caso ela esteja atualmente como MATRICULADO.
     */
    public void trancarMatricula(Long id) {
        // Garante que a matrícula exista antes de tentar alterar o status
        MatriculaAluno matriculaEncontrada = matriculaAlunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Permite o trancamento apenas para matrículas ativas (status MATRICULADO)
        if (matriculaEncontrada.getStatus()
                .equals(MatriculaAlunoStatusEnum.MATRICULADO)) {
            matriculaEncontrada.setStatus(MatriculaAlunoStatusEnum.TRANCADO);
            matriculaAlunoRepository.save(matriculaEncontrada);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Só é possivel trancar com status MATRICULADO"
            );
        }
    }

}
