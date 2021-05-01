package studio.fabrique.service;

import org.springframework.stereotype.Service;
import studio.fabrique.repository.QuestionRepository;

/**
 * Сервис по работе с вопросами
 */
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;


    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }



}
