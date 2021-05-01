package studio.fabrique.exception;

/**
 * Класс обработки ошибок приложения
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
