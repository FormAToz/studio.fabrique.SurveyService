package studio.fabrique.api.request.survey;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import studio.fabrique.api.request.QuestionRequest;

import java.util.List;

/**
 * Класс запроса для добавления/изменения опроса
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SurveyRequest {

    private final long id;
    private final String name;
    private final String description;

    @JsonProperty(value = "expiration_date")
    private final long timestampEndDate;

    private final List<QuestionRequest> questions;


    public SurveyRequest(long id, String name, String description, long timestampEndDate, List<QuestionRequest> questions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timestampEndDate = timestampEndDate;
        this.questions = questions;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getTimestampEndDate() {
        return timestampEndDate;
    }

    public List<QuestionRequest> getQuestions() {
        return questions;
    }
}
