package com.mitutor.dtos.input;


import java.io.Serializable;

public class CreateCourseInput implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer universityId;
    private String name;

    public CreateCourseInput() {
    }

    public CreateCourseInput withUniversityId(Integer universityId) {
        this.setUniversityId(universityId);
        return this;
    }

    public CreateCourseInput withName(String name) {
        this.setName(name);
        return this;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
