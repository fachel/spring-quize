package com.belov.spring.quize.repo;

import com.belov.spring.quize.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepo extends JpaRepository<Survey, Integer> {
    public void deleteById(int id);
    public Survey getSurveyById(int id);
}
