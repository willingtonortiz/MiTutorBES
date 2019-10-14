package com.mitutor.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "universities")
public class University implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "university")
	private List<Person> persons = new ArrayList<Person>();

	@OneToMany(mappedBy = "university")
	private List<Course> courses = new ArrayList<Course>();

	@OneToMany(mappedBy = "university")
	private List<TutoringOffer> tutoringOffers = new ArrayList<TutoringOffer>();

	public University() {
	}

	public University(String name) {
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

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<TutoringOffer> getTutoringOffers() {
		return tutoringOffers;
	}

	public void setTutoringOffers(List<TutoringOffer> tutoringOffers) {
		this.tutoringOffers = tutoringOffers;
	}

}
