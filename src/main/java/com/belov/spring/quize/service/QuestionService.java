package com.belov.spring.quize.service;

import com.belov.spring.quize.entity.Question;

public interface QuestionService {
    public void saveQuestion(Question question);
    public void deleteById(int id);
    public Question getQuestionById(int id);
}
