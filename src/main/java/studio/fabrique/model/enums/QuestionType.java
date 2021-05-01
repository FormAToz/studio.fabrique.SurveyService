package studio.fabrique.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Класс перечислений типов ответов на вопрос
 *
 * TEXT - ответ текстом
 * SINGLE_SELECT - ответ с выбором одного варианта
 * MULTI_SELECT - ответ с выбором нескольких вариантов
 */
public enum QuestionType {
    TEXT,
    SINGLE_SELECT,
    MULTI_SELECT;

    @JsonCreator
    public static QuestionType fromString(String key) {
        for(QuestionType type : QuestionType.values()) {
            if(type.name().equalsIgnoreCase(key)) {
                return type;
            }
        }
        return null;
    }
}
