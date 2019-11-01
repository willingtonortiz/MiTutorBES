package com.mitutor.dtos.Responses;

public class TutorRegisterResponse {
	private Integer tutorId;
	private Integer qualificationCount;
	private String description;
	private String status;
	private Double points;
	
	
	public Integer getTutorId() {
		return tutorId;
	}
	public void setTutorId(Integer tutorId) {
		this.tutorId = tutorId;
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
	public Double getPoints() {
		return points;
	}
	public void setPoints(Double points) {
		this.points = points;
	}
}
