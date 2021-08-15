package com.belov.spring.quize.repo;

import com.belov.spring.quize.entity.AnsweredQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnsweredQuestionRepo extends JpaRepository<AnsweredQuestion, Integer> {
    public AnsweredQuestion findByQuestion_IdAndUser_Id(int question_Id, int user_Id);
}
