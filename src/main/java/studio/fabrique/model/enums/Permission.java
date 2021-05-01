package studio.fabrique.model.enums;

/**
 * Класс разрешений для ролей пользователей
 */
public enum Permission {
    USER("user:write"),
    ADMIN("user:moderate");

    String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
