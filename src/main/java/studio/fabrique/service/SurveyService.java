package studio.fabrique.service;

import org.springframework.stereotype.Service;
import studio.fabrique.api.request.survey.SurveyRequest;
import studio.fabrique.api.response.ResultResponse;
import studio.fabrique.model.Survey;
import studio.fabrique.repository.SurveyRepository;

import java.time.LocalDateTime;

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
     * @param request объект SurveyRequest
     * @return ResultResponse со значением true и данными о сохраненном опросе
     */
    public ResultResponse addSurvey(SurveyRequest request) {
        return new ResultResponse(true, surveyRepository.save(fromSurveyRequest(request)));
    }

    /**
     * Метод проверки и преобразования объекта SurveyRequest в объект Survey
     * @param request объект SurveyRequest
     * @return объект Survey
     */
    private Survey fromSurveyRequest(SurveyRequest request) {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = timeService.localDateTimeFromTimestamp(request.getTimestampEndDate());

        // проверка длины названия и описания опроса
        textService.checkTitleLength(request.getName());
        textService.checkTextLength(request.getDescription());
        // проверка даты окончания опроса
        timeService.checkEndDateIsLaterThanNow(startDate, endDate);

        return new Survey(
                request.getName(),
                request.getDescription(),
                endDate
        );
    }
}
