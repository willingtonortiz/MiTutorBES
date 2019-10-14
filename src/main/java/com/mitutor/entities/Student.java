package com.mitutor.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "points")
	private Float points;

	@Column(name = "qualification_count")
	private Integer qualificationCount;

	@ManyToMany
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<Course>();

	@ManyToMany(mappedBy = "students")
	private List<TutoringSession> tutoringSessions = new ArrayList<TutoringSession>();

	@OneToOne
	@JoinColumn(name = "id")
	@MapsId
	private Person person;

	public Student() {

	}

	public Student(Float points) {
		this.points = points;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPoints() {
		return points;
	}

	public void setPoints(Float points) {
		this.points = points;
	}

	public Integer getQualificationCount() {
		return qualificationCount;
	}

	public void setQualificationCount(Integer qualificationCount) {
		this.qualificationCount = qualificationCount;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<TutoringSession> getTutoringSessions() {
		return tutoringSessions;
	}

	public void setTutoringSessions(List<TutoringSession> tutoringSessions) {
		this.tutoringSessions = tutoringSessions;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
