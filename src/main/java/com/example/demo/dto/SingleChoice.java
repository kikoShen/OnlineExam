package com.example.demo.dto;

public class SingleChoice {
    private Integer idsingleChoice;

    private String question;

    private String contenta;

    private String contentb;

    private String contentc;

    private String answer;

    public Integer getIdsingleChoice() {
        return idsingleChoice;
    }

    public void setIdsingleChoice(Integer idsingleChoice) {
        this.idsingleChoice = idsingleChoice;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getContenta() {
        return contenta;
    }

    public void setContenta(String contenta) {
        this.contenta = contenta == null ? null : contenta.trim();
    }

    public String getContentb() {
        return contentb;
    }

    public void setContentb(String contentb) {
        this.contentb = contentb == null ? null : contentb.trim();
    }

    public String getContentc() {
        return contentc;
    }

    public void setContentc(String contentc) {
        this.contentc = contentc == null ? null : contentc.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}