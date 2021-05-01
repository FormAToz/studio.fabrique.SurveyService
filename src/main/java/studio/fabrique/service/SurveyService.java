package studio.fabrique.service;

import org.springframework.stereotype.Service;
import studio.fabrique.repository.SurveyRepository;

/**
 * Сервис по работе с опросами
 */
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;


    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }



}
