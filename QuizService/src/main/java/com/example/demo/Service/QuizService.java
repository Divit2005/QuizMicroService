package com.example.demo.Service;

import com.example.demo.Feign.QuizInterface;
import com.example.demo.Model.Question;
import com.example.demo.Model.QuestionWrapper;
import com.example.demo.Model.Quiz;
import com.example.demo.Model.Response;
import com.example.demo.Repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuizInterface quizInterface;

    public String getQuiz(int no, String category, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,no) .getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);

        quizRepo.save(quiz);

        return "Quiz created successfully with ID: " + quiz.getId();
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        if (quiz.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }

        List<QuestionWrapper> questionsFromDB = quizInterface.getQuestionsById(quiz.get().getQuestionIds()).getBody();

        return new ResponseEntity<>(questionsFromDB, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quizOptional = quizRepo.findById(id);
        if (quizOptional.isEmpty()) {
            return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
        }

        int right = quizInterface.getScore(responses).getBody();

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
