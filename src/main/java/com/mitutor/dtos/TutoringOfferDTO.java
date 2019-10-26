package com.mitutor.dtos;

import com.mitutor.entities.TutoringOffer;

import java.io.Serializable;
import java.util.Date;

public class TutoringOfferDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date startTime;
    private Date endTime;
    private Integer capacity;
    private String description;

    public TutoringOfferDTO() {
    }

    public TutoringOfferDTO withId(Integer id) {
        this.setId(id);
        return this;
    }

    public TutoringOfferDTO withStartTime(Date startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public TutoringOfferDTO withEndTime(Date endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public TutoringOfferDTO withCapacity(Integer capacity) {
        this.setCapacity(capacity);
        return this;
    }

    public TutoringOfferDTO withDescription(String description) {
        this.setDescription(description);
        return this;
    }

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
}
