package studio.fabrique.api.request;

import studio.fabrique.model.enums.QuestionType;

/**
 * Класс запроса для ответов на вопросы
 */
public class AnswerRequest {

    private final String text;
    private final QuestionType type;


    public AnswerRequest(String text, QuestionType type) {
        this.text = text;
        this.type = type;
    }


    public String getText() {
        return text;
    }

    public QuestionType getType() {
        return type;
    }
}
