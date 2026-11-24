package br.com.alunoonline.api.repository;

import br.com.alunoonline.api.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório Spring Data JPA para operações de persistência da entidade {@link Professor}.
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Long id(Long id);

}
