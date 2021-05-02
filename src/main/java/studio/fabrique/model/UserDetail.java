package studio.fabrique.model;

import studio.fabrique.model.enums.QuestionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "survey_id")
    private long surveyId;

    @Column(name = "question_id")
    private long questionId;

    @Column(name = "answer_text")
    private String answerText;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_type")
    private QuestionType answerType;


    public UserDetail() {
    }

    public UserDetail(long userId, long surveyId, long questionId, String answerText, QuestionType answerType) {
        this.userId = userId;
        this.surveyId = surveyId;
        this.questionId = questionId;
        this.answerText = answerText;
        this.answerType = answerType;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public QuestionType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(QuestionType answerType) {
        this.answerType = answerType;
    }
}
