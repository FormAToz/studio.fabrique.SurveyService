package studio.fabrique.service;

import org.springframework.stereotype.Service;
import studio.fabrique.api.request.QuestionRequest;
import studio.fabrique.api.request.survey.SurveyRequest;
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

    private final SurveyService surveyService;
    private final QuestionRepository questionRepository;
    private final TextService textService;


    public QuestionService(SurveyService surveyService, QuestionRepository questionRepository, TextService textService) {
        this.surveyService = surveyService;
        this.questionRepository = questionRepository;
        this.textService = textService;
    }


    /**
     * Метод добавления нового вопроса к опросу
     * @param surveyId идентификатор опроса
     * @param request объект {@link QuestionRequest}
     * @return объект {@link ResultResponse} со значением true и данными о сохраненном вопросе
     */
    public ResultResponse addQuestionToSurvey(long surveyId, QuestionRequest request) {
        Survey survey = surveyService.getById(surveyId);
        Question question = fromQuestionRequest(request);

        question.setSurveyId(survey);
        return new ResultResponse(true, questionRepository.save(question));
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
    private Question getById(long id) {
        return questionRepository.findById(id).orElseThrow(
                () -> new ApplicationException(String.format("Вопроса с id = %d не существует", id))
        );
    }
}