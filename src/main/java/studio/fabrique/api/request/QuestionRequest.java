package studio.fabrique.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import studio.fabrique.model.enums.QuestionType;

/**
 * Класс запроса при добавлении/изменении вопроса
 */
public class QuestionRequest {

    @JsonProperty(value = "survey_id")
    private final long surveyId;

    private final long id;
    private final String text;
    private final QuestionType type;
    private final AnswerRequest answer;

    public QuestionRequest(long surveyId, long id, String text, QuestionType type, AnswerRequest answer) {
        this.surveyId = surveyId;
        this.id = id;
        this.text = text;
        this.type = type;
        this.answer = answer;
    }


    public long getId() {
        return id;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public String getText() {
        return text;
    }

    public QuestionType getType() {
        return type;
    }

    public AnswerRequest getAnswer() {
        return answer;
    }
}
