package com.mitutor.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tutoring_sessions")
public class TutoringSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "place")
	private String place;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "student_count")
	private Integer studentCount;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private Float price;

	@ManyToMany
	@JoinTable(name = "tutoring_session_topic", joinColumns = @JoinColumn(name = "tutoring_session_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
	private List<Topic> topics = new ArrayList<Topic>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tutoring_offer_id")
	private TutoringOffer tutoringOffer;

	@ManyToMany
	@JoinTable(name = "tutoring_session_student", joinColumns = @JoinColumn(name = "tutoring_session_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> students = new ArrayList<Student>();

	@OneToMany(mappedBy = "tutoringSession")
	private List<Qualification> qualifications = new ArrayList<Qualification>();

	public TutoringSession() {

	}

	public TutoringSession withId(Integer id){
		this.setId(id);
		return this;
	}

	public TutoringSession withPlace(String place){
		this.setPlace(place);
		return this;
	}

	public TutoringSession withStartTime(Date startTime){
		this.setStartTime(startTime);
		return this;
	}

	public TutoringSession withEndTime(Date endTime){
		this.setEndTime(endTime);
		return this;
	}

	public TutoringSession withStudentCount(Integer studentCount){
		this.setStudentCount(studentCount);
		return this;
	}

	public TutoringSession withDescription(String description){
		this.setDescription(description);
		return this;
	}

	public TutoringSession withPrice(Float price){
		this.setPrice(price);
		return this;
	}

	public TutoringSession addTopic(Topic topic){
		this.topics.add(topic);
		return this;
	}

	public TutoringSession withTutoringOffer(TutoringOffer tutoringOffer){
		this.setTutoringOffer(tutoringOffer);
		return this;
	}

	public TutoringSession addStudent(Student student){
		this.students.add(student);
		return this;
	}

	public TutoringSession addQualification(Qualification qualification){
		this.qualifications.add(qualification);
		return this;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public TutoringOffer getTutoringOffer() {
		return tutoringOffer;
	}

	public void setTutoringOffer(TutoringOffer tutoringOffer) {
		this.tutoringOffer = tutoringOffer;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Qualification> getQualifications() {
		return qualifications;
	}

	public void setQualifications(List<Qualification> qualifications) {
		this.qualifications = qualifications;
	}

}
