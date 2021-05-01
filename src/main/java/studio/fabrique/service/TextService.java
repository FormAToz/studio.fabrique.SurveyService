package studio.fabrique.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import studio.fabrique.exception.ApplicationException;

/**
 * Сервис для работы с текстом
 */
@Service
public class TextService {

    @Value("${text.min-title-length}")
    private int minTitleLength;

    @Value("${text.max-title-length}")
    private int maxTitleLength;

    @Value("${text.min-text-length}")
    private int minTextLength;

    @Value("${text.max-text-length}")
    private int maxTextLength;


    /**
     * Метод проверки длины текстовых заголовков
     * @param title заголовок
     * @throws ApplicationException в случае, если длина заголовка выходит за допустимые пределы
     */
    public void checkTitleLength(String title) {
        if (title.length() < minTitleLength) {
            throw new ApplicationException("Заголовок менее " + minTitleLength + " символов");
        }
        if (title.length() > maxTitleLength) {
            throw new ApplicationException("Заголовок более " + maxTitleLength + " символов");
        }
    }

    /**
     * Метод проверки длины текста с описанием
     * @param text текст с описанием
     * @throws ApplicationException в случае, если длина текста выходит за допустимые пределы
     */
    public void checkTextLength(String text) {
        if (text.length() < minTextLength) {
            throw new ApplicationException("Размер текста менее " + minTextLength + " символов");
        }

        if (text.length() > maxTextLength) {
            throw new ApplicationException("Размер текста более " + maxTextLength + " символов");
        }
    }
}
