package com.mitutor.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "topics")
public class Topic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;

	@ManyToMany(mappedBy = "topics")
	private List<TutoringOffer> tutoringOffers = new ArrayList<TutoringOffer>();

	@ManyToMany(mappedBy = "topics")
	private List<TutoringSession> tutoringSessions = new ArrayList<TutoringSession>();

	public Topic() {

	}

	public Topic(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<TutoringOffer> getTutoringOffers() {
		return tutoringOffers;
	}

	public void setTutoringOffers(List<TutoringOffer> tutoringOffers) {
		this.tutoringOffers = tutoringOffers;
	}

	public List<TutoringSession> getTutoringSessions() {
		return tutoringSessions;
	}

	public void setTutoringSessions(List<TutoringSession> tutoringSessions) {
		this.tutoringSessions = tutoringSessions;
	}

}
