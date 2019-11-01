package com.mitutor.dtos.output;

import java.io.Serializable;

public class TutorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String fullName;
    private String career;
    private Double points;

    public TutorInfo() {
    }

    public Integer getId() {
        return id;
    }

    public TutorInfo withId(Integer id) {
        this.setId(id);
        return this;
    }

    public TutorInfo withFullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public TutorInfo withCareer(String career) {
        this.setCareer(career);
        return this;
    }

    public TutorInfo withPoints(Double points) {
        this.setPoints(points);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }
}
