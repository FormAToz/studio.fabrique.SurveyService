package studio.fabrique.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import studio.fabrique.api.request.QuestionRequest;
import studio.fabrique.api.request.survey.SurveyPassedRequest;
import studio.fabrique.api.request.survey.SurveyRequest;
import studio.fabrique.api.response.ResultResponse;
import studio.fabrique.api.response.SurveyResponse;

import java.util.Optional;

@RestController
@RequestMapping("survey")
public class SurveyController {

    // ------------------ ADMIN ----------------------------

    // добавить опрос POST /survey/add
    @PostMapping("/add")
    public ResponseEntity<ResultResponse> addSurvey(SurveyRequest request) {
        return ResponseEntity.ok(new ResultResponse(true));
    }

    // изменить опрос PUT /survey/{id}/update
    @PutMapping("/{id}/update")
    public ResponseEntity<ResultResponse> updateSurvey(@PathVariable Optional<Long> id, SurveyRequest request) {
        //TODO обработать Optional
        return ResponseEntity.ok(new ResultResponse(true));
    }

    // удалить опрос DELETE /survey/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ResultResponse> deleteSurvey(@PathVariable Optional<Long> id) {
        //TODO обработать Optional
        return ResponseEntity.ok(new ResultResponse(true));
    }

    // добавить вопрос к опросу POST /survey/{id}/question-add
    @PostMapping("/{id}/question-add")
    public ResponseEntity<ResultResponse> addQuestionToSurvey(@PathVariable Optional<Long> id, QuestionRequest request) {
        //TODO обработать Optional
        return ResponseEntity.ok(new ResultResponse(true));
    }

    // изменить вопрос в опросе PUT /survey/{surveyId}/{questionId}
     @PutMapping("/{surveyId}/{questionId}")
     public ResponseEntity<ResultResponse> updateQuestionInSurvey(@PathVariable Optional<Long> surveyId,
                                                                  @PathVariable Optional<Long> questionId,
                                                                  QuestionRequest request) {
        //TODO обработать Optional
        return ResponseEntity.ok(new ResultResponse(true));
     }

    // удалить вопрос в опросе DELETE /survey/{surveyId}/{questionId}
    @DeleteMapping("/{surveyId}/{questionId}")
    public ResponseEntity<ResultResponse> deleteQuestionFromSurvey(@PathVariable Optional<Long> surveyId,
                                                                   @PathVariable Optional<Long> questionId) {
        //TODO обработать Optional
        return ResponseEntity.ok(new ResultResponse(true));
    }


    // --------------------- USER ------------------

    // получение списка активных опросов GET /survey/active
    @GetMapping("/active")
    public ResponseEntity<SurveyResponse> getActiveList(@RequestParam(defaultValue = "0") int offset,
                                                        @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(new SurveyResponse());
    }

    // прохождение опроса POST /survey/{id}/passed
    @PostMapping("/{id}/passed")
    public ResponseEntity<ResultResponse> passTheSurveys(@PathVariable Optional<Long> id, SurveyPassedRequest request) {
        //TODO доработать SurveyPassedRequest, прикрутить и реализовать связь сущности ответа на вопрос
        //TODO обработать Optional
        return ResponseEntity.ok(new ResultResponse(true));
    }

    // получение детализации по пройденным опросам GET /survey/{id}/passed
    @GetMapping("/{id}/passed")
    public ResponseEntity<SurveyResponse> getPassedSurveysByUserId(@PathVariable Optional<Long> id) {
        //TODO обработать Optional
        return ResponseEntity.ok(new SurveyResponse());
    }
}