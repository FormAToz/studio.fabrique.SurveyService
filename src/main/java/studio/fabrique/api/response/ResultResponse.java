package studio.fabrique.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import studio.fabrique.model.Question;
import studio.fabrique.model.Survey;

/**
 * Класс ответов с результатами выполнения приложения
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultResponse {

    private boolean result;
    private String message;
    private Survey survey;
    private Question question;


    public ResultResponse() {
    }

    public ResultResponse(boolean result) {
        this.result = result;
    }

    public ResultResponse(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public ResultResponse(boolean result, Survey survey) {
        this.result = result;
        this.survey = survey;
    }

    public ResultResponse(boolean result, Question question) {
        this.result = result;
        this.question = question;
    }


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
