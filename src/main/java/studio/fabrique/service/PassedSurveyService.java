package studio.fabrique.service;

import org.springframework.stereotype.Service;
import studio.fabrique.model.UserDetail;
import studio.fabrique.repository.PassedSurveyRepository;

/**
 * Сервис по работе с пройденными опросами пользователей
 */
@Service
public class PassedSurveyService {

    private final PassedSurveyRepository passedSurveyRepository;


    public PassedSurveyService(PassedSurveyRepository passedSurveyRepository) {
        this.passedSurveyRepository = passedSurveyRepository;
    }


    /**
     * Метод сохранения объекта детализации прохождения опроса в базу
     * @param userDetail несохраненный в базу объект {@link UserDetail}
     * @return сохраненный в базу объект {@link UserDetail}
     */
    public UserDetail save(UserDetail userDetail) {
        return passedSurveyRepository.save(userDetail);
    }
}
