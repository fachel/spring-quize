package com.belov.spring.quize.controller;

import com.belov.spring.quize.entity.AnsweredQuestion;
import com.belov.spring.quize.entity.Question;
import com.belov.spring.quize.entity.Survey;
import com.belov.spring.quize.entity.User;
import com.belov.spring.quize.service.AnsweredQuestionService;
import com.belov.spring.quize.service.QuestionService;
import com.belov.spring.quize.service.SurveyService;
import com.belov.spring.quize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnsweredQuestionService answeredQuestionService;

    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public String user(@RequestParam ("user_id") int user_id, Model model){
        List<Survey> surveyList = surveyService.getAllSurvey();
        model.addAttribute("surveyList", surveyList);
        model.addAttribute("user_id", user_id);
        return "view_user";
    }

    @GetMapping("/user/survey")
    public String survey(@RequestParam ("survey_id") int survey_id,
                         @RequestParam ("user_id") int user_id,
                         Model model){
        List<Question> questionList = surveyService.getSurveyById(survey_id).getQuestionList();
        model.addAttribute("user_id", user_id);
        model.addAttribute("questionList", questionList);
        return "view_client_questions_list";
    }

    @PostMapping("/user/question")
    public String question(@RequestParam ("question_id") int question_id,
                           @RequestParam ("user_id") int user_id,
                           Model model){
        Question question = questionService.getQuestionById(question_id);
        if (question.getType().trim().startsWith("radiobutton")) {
            model.addAttribute("type", "radiobutton");
            model.addAttribute("answers", getAnswers(question));
        }
        else if (question.getType().trim().startsWith("checkbox")){
            model.addAttribute("type", "checkbox");
            model.addAttribute("answers", getAnswers(question));
        }
        else {
            model.addAttribute("type", "text");
        }
        model.addAttribute("question_id", question_id);
        model.addAttribute("question", question.getText());
        model.addAttribute("user_id", user_id);
        return "view_client_question";
    }

    @PostMapping("/user/saveAnswer")
    public String saveAnswer(@RequestParam ("question_id") int question_id,
                             @RequestParam ("user_id") int user_id,
                             @RequestParam ("answ") String answer){

        AnsweredQuestion answeredQuestion = answeredQuestionService.findByQuestion_IdAndUser_Id(question_id, user_id);
        System.out.println(answeredQuestion);
        User user = userService.getUserById(user_id);
        if (answeredQuestion != null){
            answeredQuestion.setAnswer(answer);
            answeredQuestionService.save(answeredQuestion);
        }
        else
        {
            answeredQuestion =  new AnsweredQuestion(answer);
            answeredQuestion.setQuestion(questionService.getQuestionById(question_id));
            answeredQuestion.setUser(user);
            answeredQuestionService.save(answeredQuestion);
        }
        if (!user.getSurveyList().contains(questionService.getQuestionById(question_id).getSurvey())){
            user.addSurvey(questionService.getQuestionById(question_id).getSurvey());
            userService.save(user);
        }
        int survey_id = questionService.getQuestionById(question_id).getSurvey().getId();
        return "redirect:/user/survey" + "?survey_id=" + survey_id + "&user_id=" + user_id;
    }

    @GetMapping("/user/mySurveys")
    public String showMySurveys(@RequestParam ("user_id") int user_id, Model model){
        model.addAttribute("surveys", userService.getUserById(user_id).getSurveyList());
        model.addAttribute("user_id", user_id);
        return "view_my_surveys";
    }

    @GetMapping("/user/myAnswers")
    public String showMyAnswers(@RequestParam ("user_id") int user_id,
                              @RequestParam ("survey_id") int survey_id,
                              Model model){
        model.addAttribute("survey_name", surveyService.getSurveyById(survey_id).getName());
        List<AnsweredQuestion> allAnswers = userService.getUserById(user_id).getAnsweredQuestionList();
        List<AnsweredQuestion> answersBySurveyId = new ArrayList<>();
        for (AnsweredQuestion aq: allAnswers){
            if (aq.getQuestion().getSurvey().getId() == survey_id){
                answersBySurveyId.add(aq);
            }
        }
        model.addAttribute("questions", answersBySurveyId);
        model.addAttribute("user_id", user_id);
        return "view_my_questions";
    }

    private List<String> getAnswers (Question question) {
        String str = question.getType().trim();
        String result = str.substring(str.indexOf('?') + 1);
        return Arrays.asList(result.split(","));
    }

}
