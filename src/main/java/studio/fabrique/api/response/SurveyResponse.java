package studio.fabrique.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

/**
 * Класс ответа с информацией об опросе
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SurveyResponse {

    private String name;
    private String description;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime endDate;


    public SurveyResponse() {
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
