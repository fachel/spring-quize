package com.belov.spring.quize.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "surveys")
public class Survey {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start")
    private String start;

    @Column(name = "finish")
    private String finish;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;


//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "surveyList")
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name = "user_survey",
        joinColumns = @JoinColumn(name = "survey_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userList;

    @OneToMany(cascade = CascadeType.REMOVE,
                mappedBy = "survey")
    private List<Question> questionList;

    public void addToQuestionList(Question question){
        if (questionList == null){
            questionList = new ArrayList<>();
        }
        questionList.add(question);
        question.setSurvey(this);
    }

    public void addUserToSurvey(User user){
        if (userList == null){
            userList = new ArrayList<>();
        }
        userList.add(user);
    }

    public Survey() {
    }

    public Survey(String start, String finish, String description, String name) {
        this.start = start;
        this.finish = finish;
        this.description = description;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", start=" + start +
                ", finish=" + finish +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
