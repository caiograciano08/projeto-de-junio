package br.com.alunoonline.api.repository;

import br.com.alunoonline.api.model.MatriculaAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório Spring Data JPA para operações de persistência da entidade {@link MatriculaAluno}.
 */
@Repository
public interface MatriculaAlunoRepository extends JpaRepository<MatriculaAluno, Long> {

    /**
     * Busca todas as matrículas associadas a um aluno específico.
     */
    List<MatriculaAluno> findByAlunoId(Long alunoId);

}
