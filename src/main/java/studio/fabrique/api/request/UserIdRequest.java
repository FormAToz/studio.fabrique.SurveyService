package studio.fabrique.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс запроса уникального id пользователя
 */
public class UserIdRequest {

    @JsonProperty(value = "user_id")
    private long id;


    public UserIdRequest() {
    }

    public UserIdRequest(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
