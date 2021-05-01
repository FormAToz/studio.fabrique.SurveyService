package studio.fabrique.api.request;

import studio.fabrique.model.enums.QuestionType;

/**
 * Класс запроса при добавлении/изменении вопроса
 */
public class QuestionRequest {

    private final long surveyId;
    private final String text;
    private final QuestionType type;

    public QuestionRequest(long surveyId, String text, QuestionType type) {
        this.surveyId = surveyId;
        this.text = text;
        this.type = type;
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
}
