package studio.fabrique.api.request.survey;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Класс запроса пройденных опросов
 */
public class SurveyPassedRequest {

    @JsonProperty(value = "user_id")
    private final long userId;

    @JsonProperty(value = "surveys")
    private final List<SurveyRequest> surveyPassedList;


    public SurveyPassedRequest(long userId, List<SurveyRequest> surveyPassedList) {
        this.userId = userId;
        this.surveyPassedList = surveyPassedList;
    }


    public long getUserId() {
        return userId;
    }

    public List<SurveyRequest> getSurveyPassedList() {
        return surveyPassedList;
    }
}
