package studio.fabrique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.fabrique.model.Question;
import studio.fabrique.model.Survey;
import studio.fabrique.model.enums.QuestionType;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    /**
     * Метод проверки существования вопроса в базе по типу и тексту
     * @param type тип вопроса
     * @param text текст вопроса
     * @return true в случае, если вопрос существует в базе и false, если не существует
     */
    boolean existsBySurveyIdAndTypeAndTextIgnoreCase(Survey survey, QuestionType type, String text);
}
