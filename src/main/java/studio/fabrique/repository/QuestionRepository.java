package studio.fabrique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.fabrique.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
