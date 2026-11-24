package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço responsável pelo gerenciamento de professores.
 */
@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    /**
     * Salva um novo professor na base de dados.
     */
    public void criarProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    /**
     * Lista todos os professores cadastrados.
     */
    public List<Professor> listarTodosProfessores() {
        return professorRepository.findAll();
    }

    /**
     * Busca um professor pelo identificador informado.
     */
    public Optional<Professor> buscarProfessorPorId(Long id) {
        return professorRepository.findById(id);
    }

    /**
     * Remove o professor correspondente ao identificador informado.
     */
    public void deletarProfessorPorId(Long id) {
        professorRepository.deleteById(id);
    }

    /**
     * Atualiza os dados de um professor associando o identificador informado antes de salvar.
     */
    public void atualizarProfessorPorId(Long id, Professor professorEditado) {
        professorEditado.setId(id);
        professorRepository.save(professorEditado);
    }

}
