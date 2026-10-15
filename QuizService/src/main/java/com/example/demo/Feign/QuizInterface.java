package com.example.demo.Feign;

import com.example.demo.Model.Question;
import com.example.demo.Model.QuestionWrapper;
import com.example.demo.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("Question/GetAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions();

    @GetMapping("Question/Generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String Category, @RequestParam Integer numQuestions);

    @PostMapping("Question/GetQuestions{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@PathVariable List<Integer> id);

    @PostMapping("Question/GetScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
