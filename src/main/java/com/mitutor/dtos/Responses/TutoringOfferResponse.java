package com.mitutor.dtos.Responses;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TutoringOfferResponse {

    private Integer tutoringOfferId;

    private Date startTime;

    private Date endTime;

    private Integer capacity;

    private String description;

    private String tutor;

    private String course;

    private String universityName;

    private List<String> topicsName = new ArrayList<>();

    private List<TutoringSessionResponse> tutoringSessionResponses = new ArrayList<>();
    
    private Integer idTutor;
    
    public TutoringOfferResponse() {
    }

    public TutoringOfferResponse(
            Integer tutoringOfferId,
            String course,
            String tutor,
            Date startTime,
            Date endTime
    ) {
        this.tutoringOfferId = tutoringOfferId;
        this.course = course;
        this.tutor = tutor;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Integer getTutoringOfferId() {
        return tutoringOfferId;
    }

    public void setTutoringOfferId(Integer tutoringOfferId) {
        this.tutoringOfferId = tutoringOfferId;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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

	public Integer getIdTutor() {
		return idTutor;
	}

	public void setIdTutor(Integer idTutor) {
		this.idTutor = idTutor;
	}
}
