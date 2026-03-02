package dev.estudo.rita.ForumHub.repository;

import dev.estudo.rita.ForumHub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository <Curso, Long> {
    Curso save(Curso curso);

    Optional<Curso> findById(long id);
}
