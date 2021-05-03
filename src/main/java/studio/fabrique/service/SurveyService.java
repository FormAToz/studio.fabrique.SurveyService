package studio.fabrique.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import studio.fabrique.api.request.QuestionRequest;
import studio.fabrique.api.request.UserIdRequest;
import studio.fabrique.api.request.survey.SurveyPassedRequest;
import studio.fabrique.api.request.survey.SurveyRequest;
import studio.fabrique.api.response.AnswerResponse;
import studio.fabrique.api.response.QuestionResponse;
import studio.fabrique.api.response.ResultResponse;
import studio.fabrique.api.response.SurveyResponse;
import studio.fabrique.exception.ApplicationException;
import studio.fabrique.model.Answer;
import studio.fabrique.model.Survey;
import studio.fabrique.repository.SurveyRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис по работе с опросами
 */
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final TimeService timeService;
    private final TextService textService;

    public SurveyService(SurveyRepository surveyRepository, QuestionService questionService, AnswerService answerService, TimeService timeService, TextService textService) {
        this.surveyRepository = surveyRepository;
        this.questionService = questionService;
        this.answerService = answerService;
        this.timeService = timeService;
        this.textService = textService;
    }


    /**
     * Метод получения опроса по id
     *
     * @param id идентификатор опроса
     * @return объект опроса {@link Survey} из базы
     * @throws ApplicationException в случае, если опрос не найден в базе
     */
    public Survey getById(long id) {
        return surveyRepository.findById(id).orElseThrow(
                () -> new ApplicationException(String.format("Опроса с id = %d не существует", id)));
    }

    /**
     * Метод удаления опроса по id
     *
     * @param id иденификатор опроса
     * @return {@link ResultResponse} со значением true, если удаление прошло успешно
     */
    public ResultResponse deleteById(long id) {
        answerService.deleteBySurvey(getById(id));  // удаляем ответы для этого опроса
        surveyRepository.delete(getById(id));       // удаляем опрос
        return new ResultResponse(true);
    }

    /**
     * Метод добавления нового опроса
     *
     * @param request объект {@link SurveyRequest}
     * @return {@link ResultResponse} со значением true и данными о сохраненном опросе
     */
    public ResultResponse addSurvey(SurveyRequest request) {
        Survey survey = fromSurveyRequest(request);
        String surveyName = survey.getName();

        if (surveyRepository.existsByNameIgnoreCase(surveyName)) {
            throw new ApplicationException(String.format("Опрос с именем '%s' уже существует", surveyName));
        }
        return new ResultResponse(true, surveyRepository.save(survey));
    }

    /**
     * Метод проверки и преобразования объекта {@link SurveyRequest} в объект {@link Survey}
     *
     * @param request объект {@link SurveyRequest}
     * @return объект {@link Survey}
     */
    private Survey fromSurveyRequest(SurveyRequest request) {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = timeService.localDateTimeFromTimestamp(request.getTimestampEndDate());

        // проверка длины названия и описания опроса
        String name = textService.checkTitleLength(request.getName());
        String description = textService.checkTextLength(request.getDescription());
        // проверка даты окончания опроса
        timeService.checkEndDateIsLaterThanNow(startDate, endDate);

        return new Survey(name, description, endDate);
    }

    /**
     * Метод обновления данных существующего опроса
     *
     * @param id      идентификатор опроса
     * @param request объект {@link SurveyRequest}
     * @return {@link ResultResponse} со значением true и данными об измененном опросе
     */
    public ResultResponse updateSurvey(long id, SurveyRequest request) {
        Survey newSurvey = fromSurveyRequest(request);
        Survey oldSurvey = getById(id);

        oldSurvey.setName(newSurvey.getName());
        oldSurvey.setDescription(newSurvey.getDescription());
        oldSurvey.setEndDate(newSurvey.getEndDate());
        return new ResultResponse(true, surveyRepository.save(oldSurvey));
    }

    /**
     * Метод добавления нового вопроса к опросу
     *
     * @param surveyId идентификатор опроса
     * @param request  объект {@link QuestionRequest}
     * @return объект {@link ResultResponse} со значением true и данными о сохраненном вопросе
     */
    public ResultResponse addQuestionToSurvey(long surveyId, QuestionRequest request) {
        return new ResultResponse(true, questionService.addQuestionToSurvey(getById(surveyId), request));
    }

    /**
     * Метод получения всех активных опросов
     *
     * @param offset номер страницы (от 0) для вывода результатов
     * @param limit  количество опросов, которое надо вывести
     * @return список активных постов (объекты {@link SurveyResponse})
     */
    public List<SurveyResponse> getActiveSurveysList(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        return surveyRepository.finAllActiveSurveys(pageable).stream()
                .map(s -> new SurveyResponse(
                        s.getName(),
                        s.getDescription(),
                        s.getStartDate(),
                        s.getEndDate()))
                .collect(Collectors.toList());
    }

    /**
     * Метод прохождения опросов пользователя с уникальным id
     *
     * @param request объект {@link SurveyPassedRequest}
     * @return {@link ResultResponse} со значением true в случае успешного прохождения опроса
     */
    public ResultResponse passTheSurveysByUser(SurveyPassedRequest request) {
        request.getSurveyPassedList().forEach(   // проходим по списку опросов
                survey -> survey.getQuestions()  // проходим по списку вопросов
                        .forEach(question -> answerService.save(    // преобразовываем каждую запись в UserDetails и сохраняем ее
                                new Answer(
                                        request.getUserId(),                             // id пользователя
                                        question.getAnswer().getText(),                  // ответ на вопрос
                                        question.getAnswer().getType(),                  // тип ответа
                                        getById(survey.getId()),                         // опрос
                                        questionService.getById(question.getId())))));   // вопрос
        return new ResultResponse(true);
    }

    /**
     * Метод получения детализации по пройденным опросам
     * @param userIdRequest уникальный идентификатор пользователя
     * @param offset номер страницы (от 0) для вывода результатов
     * @param limit  количество опросов, которое надо вывести
     * @return список пройденных опросов определенного пользователя
     */
    public List<SurveyResponse> getDetalizationByUserId(UserIdRequest userIdRequest, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        return migrateAnswerListToSurveyResponseList(answerService.findAllByUserId(userIdRequest.getId(), pageable));
    }

    /**
     * Метод преобразования списка List<Answer> в список List<SurveyResponse> для передачи на фронт
     * @param answerList список List<Answer> из базы данных
     * @return преобразованный список List<SurveyResponse>
     */
    private List<SurveyResponse> migrateAnswerListToSurveyResponseList(List<Answer> answerList) {
        Map<Survey, List<QuestionResponse>> map = new HashMap<>();

        for (Answer a : answerList) {
            Survey key = a.getSurveyId();
            QuestionResponse questionResponse =
                    new QuestionResponse(
                            a.getQuestionId().getText(),
                            a.getQuestionId().getType(),
                            new AnswerResponse(a.getText(), a.getType()));

            if (map.containsKey(key)) {  // если ключ уже был добавлен
                map.get(key).add(questionResponse);  // добавляем questionResponse к списку
            } else {
                map.put(key, new ArrayList<>(List.of(questionResponse)));    // иначе создаем новую запись
            }
        }

        // преобразуем в список
        return map.keySet().stream()
                .map(key -> new SurveyResponse(key.getName(), key.getDescription(), map.get(key)))
                .collect(Collectors.toList());
    }
}
