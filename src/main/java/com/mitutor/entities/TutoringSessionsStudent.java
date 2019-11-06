package com.mitutor.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tutoring_session_student")
public class TutoringSessionsStudent implements Serializable{


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public TutoringSession getTutoringSession() {
		return tutoringSession;
	}

	public void setTutoringSession(TutoringSession tutoringSession) {
		this.tutoringSession = tutoringSession;
	}
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tutoring_session_id")
	private TutoringSession tutoringSession;
	
	public TutoringSessionsStudent() {
	
	}
	
	public TutoringSessionsStudent withTutoringSession(TutoringSession tutoringSession)
	{
		this.tutoringSession = tutoringSession;
		return this;
	}
	
	public TutoringSessionsStudent withStudent(Student student) {
		this.student =  student;
		return this;
	}
	public TutoringSessionsStudent(TutoringSession tutoringSession,Student student) {
		this.student = student;
		this.tutoringSession = tutoringSession;
		
	}
}
