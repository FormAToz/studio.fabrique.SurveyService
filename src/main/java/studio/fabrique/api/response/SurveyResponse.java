package studio.fabrique.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс ответа с информацией о пройденном опросе
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SurveyResponse {

    @JsonProperty(index = 0, value = "survey_name")
    private String name;

    @JsonProperty(index = 1, value = "survey_description")
    private String description;

    @JsonProperty(index = 2, value = "start_date")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime startDate;

    @JsonProperty(index = 3, value = "end_date")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime endDate;

    private List<QuestionResponse> questions;


    public SurveyResponse() {
    }

    public SurveyResponse(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public SurveyResponse(String name, String description, List<QuestionResponse> questions) {
        this.name = name;
        this.description = description;
        this.questions = questions;
    }

    public SurveyResponse(String name, String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionResponse> questions) {
        this.questions = questions;
    }
}
