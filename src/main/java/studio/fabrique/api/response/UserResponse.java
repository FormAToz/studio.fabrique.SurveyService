package studio.fabrique.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import studio.fabrique.model.enums.Role;

/**
 * Класс ответа с информацией о пользователе
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private long id;
    private String login;
    private Role role;

    public UserResponse() {
    }

    public UserResponse(long id, String login, Role role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
