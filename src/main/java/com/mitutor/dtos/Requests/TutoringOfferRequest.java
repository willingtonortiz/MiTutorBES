package com.mitutor.dtos.Requests;
import java.util.ArrayList;

import java.util.List;

public class TutoringOfferRequest {

    private Integer capacity;

    private String description;

    private Integer courseId;

    private Integer tutorId;

    private Integer universityId;

    private List<TutoringSessionsRequests> tutoringSessions = new ArrayList<>();


    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public List<TutoringSessionsRequests> getTutoringSessions() {
        return tutoringSessions;
    }

    public void setTutoringSessions(List<TutoringSessionsRequests> tutoringSessions) {
        this.tutoringSessions = tutoringSessions;
    }
}
