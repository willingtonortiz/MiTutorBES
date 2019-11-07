package com.mitutor.dtos.output;

public class QualificationResponse {
	private String fullName;
	private String comment;
	private int id;
	
	
	public QualificationResponse(String fullName,String comment, int id) {
		this.comment =  comment;
		this.fullName =  fullName;
		this.id =  id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
