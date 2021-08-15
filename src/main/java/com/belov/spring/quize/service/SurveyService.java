package com.belov.spring.quize.service;

import com.belov.spring.quize.entity.Survey;

import java.util.List;

public interface SurveyService {
    public List<Survey> getAllSurvey();
    public void saveSurvey(Survey survey);
    public void deleteSurvey(int id);
    public Survey getSurveyById(int id);
    public void delete(Survey survey);
    public void deleteUserSurvey(int user_id, int survey_id);
}
