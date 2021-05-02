package studio.fabrique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.fabrique.model.UserDetail;

@Repository
public interface PassedSurveyRepository extends JpaRepository<UserDetail, Long> {
}
