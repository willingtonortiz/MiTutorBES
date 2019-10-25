package com.mitutor.dtos.input;

import java.io.Serializable;

public class CreateTopicInput implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer courseId;
    private String name;

    public CreateTopicInput() {
    }

    public CreateTopicInput withCourseId(Integer courseId) {
        this.setCourseId(courseId);
        return this;
    }

    public CreateTopicInput withName(String name) {
        this.setName(name);
        return this;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
