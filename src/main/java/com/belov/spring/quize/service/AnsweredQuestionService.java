package com.belov.spring.quize.service;

import com.belov.spring.quize.entity.AnsweredQuestion;

public interface AnsweredQuestionService {
    public void save(AnsweredQuestion answeredQuestion);
    public AnsweredQuestion getById(int id);
    public AnsweredQuestion findByQuestion_IdAndUser_Id(int question_Id, int user_Id);
}
