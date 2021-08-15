package com.belov.spring.quize.repo;

import com.belov.spring.quize.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
    public void deleteById(int id);
}
