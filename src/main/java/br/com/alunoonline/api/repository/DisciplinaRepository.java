package br.com.alunoonline.api.repository;

import br.com.alunoonline.api.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório Spring Data JPA para operações de persistência da entidade {@link Disciplina}.
 */
@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
