package com.belov.spring.quize.service;

import com.belov.spring.quize.entity.Question;
import com.belov.spring.quize.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public void saveQuestion(Question question) {
        questionRepo.save(question);
    }

    @Override
    public void deleteById(int id) {
        questionRepo.deleteById(id);
    }

    @Override
    public Question getQuestionById(int id) {
        return questionRepo.getById(id);
    }


}
