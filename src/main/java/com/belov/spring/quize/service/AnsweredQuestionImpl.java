package com.belov.spring.quize.service;

import com.belov.spring.quize.entity.AnsweredQuestion;
import com.belov.spring.quize.repo.AnsweredQuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class AnsweredQuestionImpl implements AnsweredQuestionService {

    @Autowired
    private AnsweredQuestionRepo answeredQuestionRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(AnsweredQuestion answeredQuestion) {
        answeredQuestionRepo.save(answeredQuestion);
    }

    @Override
    public AnsweredQuestion getById(int id) {
        return answeredQuestionRepo.getById(id);
    }

    @Override
    public AnsweredQuestion findByQuestion_IdAndUser_Id(int question_Id, int user_Id) {
        return answeredQuestionRepo.findByQuestion_IdAndUser_Id(question_Id, user_Id);
    }


}
