package studio.fabrique.service;

import org.springframework.stereotype.Service;
import studio.fabrique.api.request.QuestionRequest;
import studio.fabrique.api.response.ResultResponse;
import studio.fabrique.exception.ApplicationException;
import studio.fabrique.model.Question;
import studio.fabrique.model.Survey;
import studio.fabrique.repository.QuestionRepository;

/**
 * Сервис по работе с вопросами
 */
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;
    private final TextService textService;

    public QuestionService(QuestionRepository questionRepository, AnswerService answerService, TextService textService) {
        this.questionRepository = questionRepository;
        this.answerService = answerService;
        this.textService = textService;
    }


    /**
     * Метод добавления нового вопроса к опросу
     * @param survey объект опроса {@link Survey}
     * @param request вопрос, объект {@link QuestionRequest}
     * @return сохраненный в базу объект {@link Question}
     */
    public Question addQuestionToSurvey(Survey survey, QuestionRequest request) {
        Question question = fromQuestionRequest(request);
        if (questionRepository.existsBySurveyIdAndTypeAndTextIgnoreCase(survey, question.getType(), question.getText())) {
            throw new ApplicationException(String.format(
                    "Вопрос с типом '%s' и текстом '%s' уже существует", question.getType(), question.getText()));
        }

        question.setSurveyId(survey);
        return questionRepository.save(question);
    }

    /**
     * Метод проверки и преобразования объекта {@link QuestionRequest} в объект {@link Question}
     * @param request объект {@link QuestionRequest}
     * @return объект {@link Question}
     */
    private Question fromQuestionRequest(QuestionRequest request) {
        return new Question(
                textService.checkTextLength(request.getText()),
                request.getType());
    }

    /**
     * Метод обновления данных существующего вопроса
     * @param id идентификатор вопроса
     * @param request объект {@link QuestionRequest}
     * @return {@link ResultResponse} со значением true и данными об измененном вопросе
     */
    public ResultResponse updateQuestion(long id, QuestionRequest request) {
        String text = textService.checkTextLength(request.getText());
        Question question = getById(id);

        question.setText(text);
        question.setType(request.getType());
        return new ResultResponse(true, questionRepository.save(question));
    }

    /**
     * Метод получения вопроса по id
     * @param id идентификатор вопроса
     * @return объект вопроса {@link Question} из базы
     * @throws ApplicationException в случае, если вопрос не найден в базе
     */
    public Question getById(long id) {
        return questionRepository.findById(id).orElseThrow(
                () -> new ApplicationException(String.format("Вопроса с id = %d не существует", id))
        );
    }

    /**
     * Метод удаления вопроса по id
     * @param id иденификатор вопроса
     * @return {@link ResultResponse} со значением true, если удаление прошло успешно
     */
    public ResultResponse deleteById(long id) {
        answerService.deleteByQuestion(getById(id));   // удаляем все ответы для этого вопроса
        questionRepository.delete(getById(id));        // удаляем вопрос
        return new ResultResponse(true);
    }
}