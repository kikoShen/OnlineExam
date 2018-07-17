package com.example.demo.dto;

public class QuestionBank {
    private Integer idquestionBank;

    private String job;

    private String level;

    private String type;

    public Integer getIdquestionBank() {
        return idquestionBank;
    }

    public void setIdquestionBank(Integer idquestionBank) {
        this.idquestionBank = idquestionBank;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}