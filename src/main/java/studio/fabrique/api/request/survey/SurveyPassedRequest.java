package studio.fabrique.api.request.survey;

import studio.fabrique.model.Survey;

import java.util.List;

/**
 * Класс запроса пройденных опросов
 */
public class SurveyPassedRequest {

    private final long userId;
    private final List<Survey> surveyList;

    public SurveyPassedRequest(long userId, List<Survey> surveyList) {
        this.userId = userId;
        this.surveyList = surveyList;
    }

    public long getUserId() {
        return userId;
    }

    public List<Survey> getSurveyList() {
        return surveyList;
    }
}
