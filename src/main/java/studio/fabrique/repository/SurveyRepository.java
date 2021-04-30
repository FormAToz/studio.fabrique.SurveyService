package studio.fabrique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.fabrique.model.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
