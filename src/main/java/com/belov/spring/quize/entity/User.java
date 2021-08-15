package com.belov.spring.quize.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name = "user_survey",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "survey_id"))
    private List<Survey> surveyList;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<AnsweredQuestion> answeredQuestionList;

    public void addAnswrQuestionToList(AnsweredQuestion answeredQuestion){
        if (answeredQuestionList == null){
            answeredQuestionList = new ArrayList<>();
        }
        answeredQuestionList.add(answeredQuestion);
        answeredQuestion.setUser(this);
    }


    public void addSurvey(Survey survey){
        if (surveyList == null){
            surveyList = new ArrayList<>();
        }
        surveyList.add(survey);
    }

    public User() {
    }

    public User(String username, String password, int enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public List<Survey> getSurveyList() {
        return surveyList;
    }

    public void setSurveyList(List<Survey> surveyList) {
        this.surveyList = surveyList;
    }

    public List<AnsweredQuestion> getAnsweredQuestionList() {
        return answeredQuestionList;
    }
}
