package com.mitutor.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tutors")
public class Tutor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "points")
	private Double points;

	@Column(name = "qualification_count")
	private Integer qualificationCount;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "tutor")
	private List<TutoringOffer> tutoringOffers = new ArrayList<TutoringOffer>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tutor_course", joinColumns = @JoinColumn(name = "tutor_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<Course>();

	@OneToOne
	@JoinColumn(name = "id")
	@MapsId
	private Person person;

	@OneToMany(mappedBy = "tutor")
	private List<AvailabilityDay> availabilityDays = new ArrayList<AvailabilityDay>();

	public Tutor() {

	}

	public Tutor(String description, Double points, Integer qualificationCount) {
		this.description = description;
		this.points = points;
		this.qualificationCount = qualificationCount;
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

	public List<TutoringOffer> getTutoringOffers() {
		return tutoringOffers;
	}

	public void setTutoringOffers(List<TutoringOffer> tutoringOffers) {
		this.tutoringOffers = tutoringOffers;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<AvailabilityDay> getAvailabilityDays() {
		return availabilityDays;
	}

	public void setAvailabilityDays(List<AvailabilityDay> availabilityDays) {
		this.availabilityDays = availabilityDays;
	}

}
