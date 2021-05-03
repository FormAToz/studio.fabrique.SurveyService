package studio.fabrique.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import studio.fabrique.model.Answer;
import studio.fabrique.model.Question;
import studio.fabrique.model.Survey;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(value = "from Answer a where a.userId = ?1")
    List<Answer> finAllByUserId(long id, Pageable pageable);

    @Transactional
    List<Answer> deleteByQuestionId(Question question);

    @Transactional
    List<Answer> deleteBySurveyId(Survey survey);
}
