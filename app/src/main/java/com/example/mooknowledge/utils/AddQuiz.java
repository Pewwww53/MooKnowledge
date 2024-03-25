package com.example.mooknowledge.utils;

public class AddQuiz {
    public String question;
    public String []choices;
    public char answer;
    public int fontSize;

    public AddQuiz( String question, String []choices, char answer, int fontSize) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
        this.fontSize = fontSize;
    }
}
