package com.example.question_service.Repo;

import com.example.question_service.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
    @Query(value = "SELECT Q.id FROM Question Q WHERE Q.category=:category ORDER BY RANDOM() LIMIT :no",nativeQuery = true)
    List<Integer> getQuiz(Integer no, String category);
}
