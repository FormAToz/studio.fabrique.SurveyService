package studio.fabrique.api.request;

/**
 * Класс запроса регистрации и авторизации
 */
public class LoginRequest {
    private final String login;
    private final String password;

    public LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
