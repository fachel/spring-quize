package com.belov.spring.quize.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text")
    private String text;

    @Column(name = "type")
    private String type;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "id_survey")
    private Survey survey;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "question")
    private List<AnsweredQuestion> answeredQuestionList;

    public void addAnswrQuestionToList(AnsweredQuestion answeredQuestion){
        if (answeredQuestionList == null){
            answeredQuestionList = new ArrayList<>();
        }
        answeredQuestionList.add(answeredQuestion);
        answeredQuestion.setQuestion(this);
    }

    public Question() {
    }

    public Question(String text, String type) {
        this.text = text;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
