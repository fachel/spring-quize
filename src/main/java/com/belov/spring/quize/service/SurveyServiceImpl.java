package com.belov.spring.quize.service;

import com.belov.spring.quize.entity.Survey;
import com.belov.spring.quize.repo.SurveyRepo;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepo surveyRepo;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Survey> getAllSurvey() {
        return surveyRepo.findAll();
    }

    @Override
    public void saveSurvey(Survey survey) {
        surveyRepo.save(survey);
    }

    @Override
    public void deleteSurvey(int id) {
        surveyRepo.deleteById(id);
    }

    @Override
    public Survey getSurveyById(int id) {
       return surveyRepo.getSurveyById(id);
    }

    @Override
    public void delete(Survey survey) {
        surveyRepo.delete(survey);
    }

    @Transactional
    @Override
    public void deleteUserSurvey(int user_id, int survey_id) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "delete from user_survey where user_id = " + user_id + " and survey_id = " + survey_id;
        SQLQuery query = session.createSQLQuery(sql);
    }
}
