package com.example.question_service.Service;

import com.example.question_service.Model.Question;
import com.example.question_service.Model.QuestionWrapper;
import com.example.question_service.Model.Response;
import com.example.question_service.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<List<Question>> getAllQestions() {
        try{
            return new ResponseEntity<>(questionRepo.findAll(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQuestions) {
        List<Integer> questions = questionRepo.getQuiz(numQuestions, category);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionById(List<Integer> id) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer Id:id){
            questions.add(questionRepo.findById(Id).get());
        }

        for(Question question:questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());

            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int cnt = 0;

        for(Response response:responses){
            if(questionRepo.findById(response.getId()).orElseThrow(()-> new RuntimeException("question not found")).getRightAnswer().equals(response.getResponse())) {
                cnt++;
            }
        }
        return new ResponseEntity<>(cnt,HttpStatus.OK);
    }
}
