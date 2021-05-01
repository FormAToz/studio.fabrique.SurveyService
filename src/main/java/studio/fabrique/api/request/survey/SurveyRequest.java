package studio.fabrique.api.request.survey;

import com.fasterxml.jackson.annotation.JsonProperty;
import studio.fabrique.model.Question;

import java.util.List;

/**
 * Класс запроса для добавления/изменения опроса
 */
public class SurveyRequest {

    private final String name;
    private final String description;

    @JsonProperty(value = "expiration_date")
    private final long timestampEndDate;


    public SurveyRequest(String name, String description, long timestampEndDate) {
        this.name = name;
        this.description = description;
        this.timestampEndDate = timestampEndDate;
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
}
