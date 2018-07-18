package com.example.demo.dto;

public class Paper {
    private Integer idpaper;

    private Integer idteacher;

    private String starttime;

    private String endtime;

    private String job;

    private String level;

    private String qarray;

    private String status;

    public Integer getIdpaper() {
        return idpaper;
    }

    public void setIdpaper(Integer idpaper) {
        this.idpaper = idpaper;
    }

    public Integer getIdteacher() {
        return idteacher;
    }

    public void setIdteacher(Integer idteacher) {
        this.idteacher = idteacher;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
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

    public String getQarray() {
        return qarray;
    }

    public void setQarray(String qarray) {
        this.qarray = qarray == null ? null : qarray.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}