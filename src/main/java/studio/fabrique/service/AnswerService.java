package studio.fabrique.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import studio.fabrique.model.Answer;
import studio.fabrique.model.Question;
import studio.fabrique.model.Survey;
import studio.fabrique.repository.AnswerRepository;

import java.util.List;

/**
 * Сервис по работе с ответами
 */
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    /**
     * Метод сохранения нового ответа в базу
     * @param answer обект ответа {@link Answer}
     * @return сохраненный объект ответа {@link Answer}
     */
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> findAllByUserId(long id, Pageable pageable) {
        return answerRepository.finAllByUserId(id, pageable);
    }

    /**
     * Метод удаления всех ответов по объекту вопроса
     * @param question объект {@link Question} вопроса
     */
    public void deleteByQuestion(Question question) {
        answerRepository.deleteByQuestionId(question);
    }

    /**
     * Метод удаления всех ответов по объекту опроса
     * @param survey объект {@link Survey} опроса
     */
    public void deleteBySurvey(Survey survey) {
        answerRepository.deleteBySurveyId(survey);
    }
}
