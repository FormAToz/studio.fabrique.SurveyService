package studio.fabrique.api.response;

import studio.fabrique.model.enums.QuestionType;

/**
 * Класс ответа с информацией о вопросе
 */
public class QuestionResponse {

    private long id;
    private String text;
    private QuestionType type;

    public QuestionResponse() {
    }

    public QuestionResponse(long id, String text, QuestionType type) {
        this.id = id;
        this.text = text;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}
