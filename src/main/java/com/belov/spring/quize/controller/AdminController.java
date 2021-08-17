package com.belov.spring.quize.controller;

import com.belov.spring.quize.entity.Question;
import com.belov.spring.quize.entity.Survey;
import com.belov.spring.quize.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String main(){
        int id = userService.getUserByName(getCurrentUsername()).getId();
        String auth = authorityService.getAuthorityByUserId(id).getAuthority().trim();
        if (auth.equals("ROLE_ADMIN")){
            return "redirect:/admin";
        }
        else
            return "redirect:/user/" + "?user_id=" + id;
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("allSurveys", surveyService.getAllSurvey());
        return "view_admin";
    }

    @GetMapping("/admin/addSurvey")
    public String addSurvey(Model model){
        model.addAttribute("survey", new Survey());
        return "view_new_survey";
    }

    @PostMapping("/admin/saveSurvey")
    public String saveSurvey(@ModelAttribute ("survey") Survey survey){
        surveyService.saveSurvey(survey);
        return "redirect:/admin";
    }

    @GetMapping("/admin/deleteSurvey")
    public String deleteSurvey(@RequestParam ("survey_id") int survey_id){
        Survey survey = surveyService.getSurveyById(survey_id);
        surveyService.delete(survey);
        return "redirect:/admin";
    }

    @PostMapping("/admin/updateSurvey")
    public String updateSurvey(@RequestParam ("id") int id, Model model){
        Survey survey = surveyService.getSurveyById(id);
        model.addAttribute("survey", survey);
        return "view_new_survey";
    }

    @PostMapping("/admin/questions")
    public String showQuestions(@RequestParam ("id") int id,Model model){
        List<Question> list = surveyService.getSurveyById(id).getQuestionList();
        model.addAttribute("questions", list);
        model.addAttribute("survey_id", id);
        return "view_questions";
    }

    @GetMapping("/admin/addQuestion")
    public String addQuestion(@RequestParam ("survey_id") int id, Model model){
        model.addAttribute("survey_id", id);
        model.addAttribute("question", new Question());
        return "view_new_question";
    }

    @PostMapping("/admin/saveQuestion")
    public String saveQuestion(@ModelAttribute ("question") Question question, @RequestParam ("survey_id") int id, Model model){
        question.setSurvey(surveyService.getSurveyById(id));
        questionService.saveQuestion(question);
        List<Question> list = surveyService.getSurveyById(id).getQuestionList();
        model.addAttribute("questions", list);
        model.addAttribute("survey_id", id);
        return "view_questions";
    }

    @GetMapping("/admin/deleteQuestion")
    public String deleteQuestion(@RequestParam ("question_id") int question_id, @RequestParam ("survey_id") int survey_id, Model model){
        questionService.deleteById(question_id);
        List<Question> list = surveyService.getSurveyById(survey_id).getQuestionList();
        model.addAttribute("questions", list);
        model.addAttribute("survey_id", survey_id);
        return "view_questions";
    }

    @PostMapping("/admin/updateQuestion")
    public String updateQuestion(@RequestParam ("question_id") int question_id, @RequestParam ("survey_id") int survey_id, Model model){
        Question question = questionService.getQuestionById(question_id);
        model.addAttribute("question", question);
        model.addAttribute("survey_id", survey_id);
        return "view_new_question";
    }


    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
