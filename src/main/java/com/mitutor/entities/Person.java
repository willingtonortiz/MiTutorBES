package com.mitutor.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "lastname", nullable = false, length = 50)
	private String lastName;

	@Column(name = "semester", nullable = false)
	private Integer semester;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id", nullable = false)
	private University university;

	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	private User user;

	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	private Student student;

	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	private Tutor tutor;

	@OneToMany(mappedBy = "adressee")
	private List<Qualification> receivedQualifications;

	@OneToMany(mappedBy = "adresser")
	private List<Qualification> givenQualifications;

	@OneToMany(mappedBy = "person")
	private List<Suscription> suscriptions = new ArrayList<Suscription>();

	public Person() {

	}

	public Person(String name, String lastname, Integer semester) {
		this.name = name;
		this.lastName = lastname;
		this.semester = semester;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public List<Qualification> getReceivedQualifications() {
		return receivedQualifications;
	}

	public void setReceivedQualifications(List<Qualification> receivedQualifications) {
		this.receivedQualifications = receivedQualifications;
	}

	public List<Qualification> getGivenQualifications() {
		return givenQualifications;
	}

	public void setGivenQualifications(List<Qualification> givenQualifications) {
		this.givenQualifications = givenQualifications;
	}

	public List<Suscription> getSuscriptions() {
		return suscriptions;
	}

	public void setSuscriptions(List<Suscription> suscriptions) {
		this.suscriptions = suscriptions;
	}

}
