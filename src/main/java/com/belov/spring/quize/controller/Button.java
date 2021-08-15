package com.belov.spring.quize.controller;

import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public class Button {
    private int question_id;
    private String question;
    private String type;
    private List<String> answrList;

    public Button(int id_question, String question, String type, List<String> answrList) {
        this.question_id = id_question;
        this.question = question;
        this.type = type;
        this.answrList = answrList;
    }

    public Button() {
    }

    @Override
    public String toString() {
        return "Button{" +
                "question_id=" + question_id +
                ", question='" + question + '\'' +
                ", type='" + type + '\'' +
                ", answrList=" + answrList +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswrList() {
        return answrList;
    }

    public void setAnswrList(List<String> answrList) {
        this.answrList = answrList;
    }
}
