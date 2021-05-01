package studio.fabrique.service;

import org.springframework.stereotype.Service;
import studio.fabrique.exception.ApplicationException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Сервис для работы со временем
 */
@Service
public class TimeService {

    /**
     * Метод преобразования timestamp(long) в LocalDateTime
     * @param timestamp значение long (timestamp в секундах)
     * @return LocalDateTime
     */
    public LocalDateTime localDateTimeFromTimestamp(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
    }

    /**
     * Метод проверки даты окончания опроса
     * @param startDate дата создания(старта) опроса
     * @param endDate дата окончания опроса
     * @throws ApplicationException в случае, если дата окончания ранее, чем сейчас или даты создания
     */
    public void checkEndDateIsLaterThanNow(LocalDateTime startDate, LocalDateTime endDate) {
        if (endDate.isBefore(startDate)) {
            throw new ApplicationException("Невозможно установить дату окончания ранее, чем дата старта");
        }
        if (endDate.isBefore(LocalDateTime.now())) {
            throw new ApplicationException("Невозможно установить дату окончания ранее, чем сейчас");
        }
    }
}
