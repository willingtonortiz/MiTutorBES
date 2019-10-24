package com.mitutor.dtos;

import java.io.Serializable;

public class TutorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Double points;

    private Integer qualificationCount;

    private String description;

    private String status;

    public TutorDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Integer getQualificationCount() {
        return qualificationCount;
    }

    public void setQualificationCount(Integer qualificationCount) {
        this.qualificationCount = qualificationCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
