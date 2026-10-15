package com.example.question_service.Controller;


import com.example.question_service.Model.Question;
import com.example.question_service.Model.QuestionWrapper;
import com.example.question_service.Model.Response;
import com.example.question_service.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/GetAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQestions();
    }

    @GetMapping("/Generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String Category, @RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(Category, numQuestions);
    }

    @PostMapping("/GetQuestions{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@PathVariable List<Integer> id){
        return questionService.getQuestionById(id);
    }

    @PostMapping("/GetScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
