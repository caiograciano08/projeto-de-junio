package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço responsável pelas regras de negócio e persistência de
 * alunos.
 */
@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    /**
     * Salva um novo aluno na base de dados.
     */
    public void criarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    /**
     * Retorna a lista completa de alunos cadastrados.
     */
    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    /**
     * Consulta um aluno pelo identificador informado.
     */
    public Optional<Aluno> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }

    /**
     * Remove o registro do aluno correspondente ao identificador informado.
     */
    public void deletarAlunoPorId(Long id) {
        alunoRepository.deleteById(id);
    }

    /**
     * Atualiza os dados de um aluno associando o identificador informado antes de
     * salvar.
     */
    public void atualizarAlunoPorId(Long id, Aluno alunoEditado) {
        alunoEditado.setId(id);
        alunoRepository.save(alunoEditado);
    }

}
