package studio.fabrique.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import studio.fabrique.api.request.QuestionRequest;
import studio.fabrique.api.request.UserIdRequest;
import studio.fabrique.api.request.survey.SurveyPassedRequest;
import studio.fabrique.api.request.survey.SurveyRequest;
import studio.fabrique.api.response.ResultResponse;
import studio.fabrique.api.response.SurveyResponse;
import studio.fabrique.service.QuestionService;
import studio.fabrique.service.SurveyService;

import java.util.List;

@RestController
@RequestMapping("survey")
public class SurveyController {

    private final SurveyService surveyService;
    private final QuestionService questionService;


    public SurveyController(SurveyService surveyService, QuestionService questionService) {
        this.surveyService = surveyService;
        this.questionService = questionService;
    }

    // ------------------ ADMIN ----------------------------

    // добавить опрос POST /survey/add
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('user:moderate')")
    public ResponseEntity<ResultResponse> addSurvey(@RequestBody SurveyRequest request) {
        return ResponseEntity.ok(surveyService.addSurvey(request));
    }

    // изменить опрос PUT /survey/{id}/update
    @PutMapping("/{id}/update")
    @PreAuthorize("hasAuthority('user:moderate')")
    public ResponseEntity<ResultResponse> updateSurvey(@PathVariable long id,
                                                       @RequestBody  SurveyRequest request) {
        return ResponseEntity.ok(surveyService.updateSurvey(id, request));
    }

    // удалить опрос DELETE /survey/{id}
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:moderate')")
    public ResponseEntity<ResultResponse> deleteSurvey(@PathVariable long id) {
        return ResponseEntity.ok(surveyService.deleteById(id));
    }

    // добавить вопрос к опросу POST /survey/{id}/question
    @PostMapping("/{id}/question")
    @PreAuthorize("hasAuthority('user:moderate')")
    public ResponseEntity<ResultResponse> addQuestionToSurvey(@PathVariable long id,
                                                              @RequestBody QuestionRequest request) {
        return ResponseEntity.ok(surveyService.addQuestionToSurvey(id, request));
    }

    // изменить вопрос в опросе PUT /survey/question/{id}
     @PutMapping("/question/{id}")
     @PreAuthorize("hasAuthority('user:moderate')")
     public ResponseEntity<ResultResponse> updateQuestionInSurvey(@PathVariable long id,
                                                                  @RequestBody QuestionRequest request) {
        return ResponseEntity.ok(questionService.updateQuestion(id, request));
     }

    // удалить вопрос в опросе DELETE /survey/question/{id}
    @DeleteMapping("/question/{id}")
    @PreAuthorize("hasAuthority('user:moderate')")
    public ResponseEntity<ResultResponse> deleteQuestionFromSurvey(@PathVariable long id) {
        return ResponseEntity.ok(questionService.deleteById(id));
    }

    // --------------------- USER ------------------

    // получение списка активных опросов GET /survey/active
    @GetMapping("/active")
    public ResponseEntity<List<SurveyResponse>> getActiveList(@RequestParam(defaultValue = "0") int offset,
                                                              @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(surveyService.getActiveSurveysList(offset, limit));
    }

    // прохождение опроса POST /survey/pass
    @PostMapping("/pass")
    public ResponseEntity<ResultResponse> passTheSurveys(@RequestBody SurveyPassedRequest request) {
        return ResponseEntity.ok(surveyService.passTheSurveysByUser(request));
    }

    // получение детализации по пройденным опросам GET /survey/passed
    @GetMapping("/passed")
    public ResponseEntity<List<SurveyResponse>> getPassedSurveysByUserId(@RequestBody UserIdRequest userIdRequest,
                                                                                                @RequestParam(defaultValue = "0") int offset,
                                                                                                @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(surveyService.getDetalizationByUserId(userIdRequest, offset, limit));
    }
}