package studio.fabrique.api.response;

import studio.fabrique.model.enums.QuestionType;

/**
 * Класс ответа с информацией об ответе на вопрос
 */
public class AnswerResponse {

    private String text;
    private QuestionType type;


    public AnswerResponse() {
    }

    public AnswerResponse(String text, QuestionType type) {
        this.text = text;
        this.type = type;
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
