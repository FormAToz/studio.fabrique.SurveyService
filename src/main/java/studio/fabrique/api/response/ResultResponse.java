package studio.fabrique.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Класс ответов приложения
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultResponse {

    private boolean result;
    private UserResponse user;
    private String message;

    public ResultResponse() {
    }

    public ResultResponse(boolean result) {
        this.result = result;
    }

    public ResultResponse(boolean result, UserResponse user) {
        this.result = result;
        this.user = user;
    }

    public ResultResponse(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
