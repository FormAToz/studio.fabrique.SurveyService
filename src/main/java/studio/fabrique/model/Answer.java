package studio.fabrique.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import studio.fabrique.model.enums.QuestionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Класс сущности ответа на вопрос
 */
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @JsonProperty(value = "user_id")
    @Column(name = "user_id")
    private long userId;

    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @JsonProperty(value = "survey")
    @OneToOne
    @JoinColumn(name = "survey_id")
    private Survey surveyId;

    @JsonProperty(value = "question")
    @OneToOne
    @JoinColumn(name = "question_id")
    private Question questionId;

    public Answer() {
    }


    public Answer(long userId, String text, QuestionType type, Survey surveyId, Question questionId) {
        this.userId = userId;
        this.text = text;
        this.type = type;
        this.surveyId = surveyId;
        this.questionId = questionId;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    public Survey getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Survey surveyId) {
        this.surveyId = surveyId;
    }
}
