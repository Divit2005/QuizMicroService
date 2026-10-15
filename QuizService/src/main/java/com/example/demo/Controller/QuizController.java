package com.example.demo.Controller;


import com.example.demo.Model.QuestionWrapper;
import com.example.demo.Model.Response;
import com.example.demo.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("/CreateQuiz")
    public String getQuiz(@RequestParam int no, @RequestParam String category, @RequestParam String title){
        return quizService.getQuiz(no, category, title);
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }
}
