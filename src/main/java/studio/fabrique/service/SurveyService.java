package studio.fabrique.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import studio.fabrique.api.request.survey.SurveyRequest;
import studio.fabrique.api.response.ResultResponse;
import studio.fabrique.api.response.SurveyResponse;
import studio.fabrique.exception.ApplicationException;
import studio.fabrique.model.Survey;
import studio.fabrique.repository.SurveyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис по работе с опросами
 */
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final TimeService timeService;
    private final TextService textService;


    public SurveyService(SurveyRepository surveyRepository, TimeService timeService, TextService textService) {
        this.surveyRepository = surveyRepository;
        this.timeService = timeService;
        this.textService = textService;
    }


    /**
     * Метод добавления нового опроса
     * @param request объект {@link SurveyRequest}
     * @return {@link ResultResponse} со значением true и данными о сохраненном опросе
     */
    public ResultResponse addSurvey(SurveyRequest request) {
        return new ResultResponse(true, surveyRepository.save(fromSurveyRequest(request)));
    }

    /**
     * Метод проверки и преобразования объекта {@link SurveyRequest} в объект {@link Survey}
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
     * Метод получения опроса по id
     * @param id идентификатор опроса
     * @return объект опроса {@link Survey} из базы
     * @throws ApplicationException в случае, если опрос не найден в базе
     */
    public Survey getById(long id) {
        return surveyRepository.findById(id).orElseThrow(
                () -> new ApplicationException(String.format("Опроса с id = %d не существует", id)));
    }

    /**
     * Метод обновления данных существующего опроса
     * @param id идентификатор опроса
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
     * Метод удаления опроса по id
     * @param id иденификатор опроса
     * @return {@link ResultResponse} со значением true, если удаление прошло успешно
     */
    public ResultResponse deleteById(long id) {
        surveyRepository.delete(getById(id));
        return new ResultResponse(true);
    }

    /**
     * Метод получения всех активных опросов
     * @param offset номер страницы (от 0) для вывода результатов
     * @param limit количество опросов, которое надо вывести
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
}
