package com.mitutor.dtos;

import java.io.Serializable;

public class CourseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;

    public CourseDTO() {
    }

    public CourseDTO withId(Integer id) {
        this.setId(id);
        return this;
    }

    public CourseDTO withName(String name) {
        this.setName(name);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
