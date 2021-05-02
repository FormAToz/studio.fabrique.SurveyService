package studio.fabrique.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс опросов
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @Column(name = "start_date")
    private final LocalDateTime startDate = LocalDateTime.now();

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @JsonProperty(value = "questions")
    @OneToMany(mappedBy = "surveyId")
    private List<Question> questionList;


    public Survey() {
    }

    public Survey(String name, String description, LocalDateTime endDate) {
        this.name = name;
        this.description = description;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
