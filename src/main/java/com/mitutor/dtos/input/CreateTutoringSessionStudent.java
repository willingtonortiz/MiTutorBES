package com.mitutor.dtos.input;

public class CreateTutoringSessionStudent {
	
	private Integer tutoring_session_id;
	private Integer student_id;
	
	
	public Integer getTutoring_session_id() {
		return tutoring_session_id;
	}
	public void setTutoring_session_id(Integer tutoring_session_id) {
		this.tutoring_session_id = tutoring_session_id;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	
}
