package com.mitutor.converters.Output;

import com.mitutor.entities.TutoringOffer;

import java.io.Serializable;
import java.util.Date;

public class TutoringOfferInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String courseName;
    private String tutorName;
    private Date startTime;
    private Date endTime;

    public TutoringOfferInfo() {

    }

    public TutoringOfferInfo withId(Integer id) {
        this.setId(id);
        return this;
    }

    public TutoringOfferInfo withCourseName(String courseName) {
        this.setCourseName(courseName);
        return this;
    }

    public TutoringOfferInfo withTutorName(String tutorName) {
        this.setTutorName(tutorName);
        return this;
    }

    public TutoringOfferInfo withStartTime(Date startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public TutoringOfferInfo withEndTime(Date endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
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
}
