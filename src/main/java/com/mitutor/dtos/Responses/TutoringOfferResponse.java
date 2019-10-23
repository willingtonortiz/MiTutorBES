package com.mitutor.dtos.Responses;

import com.mitutor.entities.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TutoringOfferResponse {

    private Integer id;

    private Date startTime;

    private Date endTime;

    private Integer capacity;

    private String description;

    private String tutorName;

    private String courseName;

    private String universityName;

    private List<String> topicsName = new ArrayList<>();

    private List<TutoringSessionResponse> tutoringSessionResponses = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

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

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public List<String> getTopicsName() {
        return topicsName;
    }

    public void setTopicsName(List<String> topicsName) {
        this.topicsName = topicsName;
    }

    public List<TutoringSessionResponse> getTutoringSessionResponses() {
        return tutoringSessionResponses;
    }

    public void setTutoringSessionResponses(List<TutoringSessionResponse> tutoringSessionResponses) {
        this.tutoringSessionResponses = tutoringSessionResponses;
    }
}
