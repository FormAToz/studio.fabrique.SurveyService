package studio.fabrique.api.response;

import studio.fabrique.model.enums.Role;

/**
 * Класс ответа с информацией о пользователе
 */
public class UserResponse {

    private final long id;
    private final String login;
    private final Role role;

    public UserResponse(long id, String login, Role role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }
}
