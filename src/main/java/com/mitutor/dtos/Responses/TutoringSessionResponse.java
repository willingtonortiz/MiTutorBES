package com.mitutor.dtos.Responses;

import com.mitutor.entities.Qualification;
import com.mitutor.entities.Student;
import com.mitutor.entities.Topic;
import com.mitutor.entities.TutoringOffer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TutoringSessionResponse {

    private Integer id;

    private String place;

    private Date startTime;

    private Date endTime;

    private Integer studentCount;

    private String description;

    private Float price;

    private List<String> topicsName = new ArrayList<>();

   //STUDENT DTO


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<String> getTopicsName() {
        return topicsName;
    }

    public void setTopicsName(List<String> topicsName) {
        this.topicsName = topicsName;
    }
}
