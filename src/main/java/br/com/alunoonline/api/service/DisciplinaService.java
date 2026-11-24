package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço responsável pelas operações de gerenciamento de disciplinas.
 */
@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    /**
     * Persiste uma nova disciplina na base de dados.
     */
    public void criarDisciplina(Disciplina disciplina) {
        disciplinaRepository.save(disciplina);
    }

    /**
     * Retorna todas as disciplinas cadastradas.
     */
    public List<Disciplina> listarTodasDisciplinas() {
        return disciplinaRepository.findAll();
    }

    /**
     * Busca uma disciplina pelo seu identificador.
     */
    public Optional<Disciplina> buscarDisciplinaPorId(Long id) {
        return disciplinaRepository.findById(id);
    }

    /**
     * Exclui a disciplina correspondente ao identificador informado.
     */
    public void deletarDisciplinaPorId(Long id) {
        disciplinaRepository.deleteById(id);
    }

    /**
     * Atualiza os dados de uma disciplina existente com base no identificador informado.
     */
    public void atualizarDisciplinaPorId(Long id, Disciplina disciplinaEditada) {
        disciplinaEditada.setId(id);
        disciplinaRepository.save(disciplinaEditada);
    }

}
