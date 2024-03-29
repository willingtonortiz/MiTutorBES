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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "course")
    private List<Topic> topics = new ArrayList<Topic>();

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<Student>();

    @OneToMany(mappedBy = "course")
    private List<TutorCourse> tutorCourses = new ArrayList<TutorCourse>();

    @OneToMany(mappedBy = "course")
    private List<TutoringOffer> tutoringOffers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course withId(Integer id) {
        this.setId(id);
        return this;
    }

    public Course withName(String name) {
        this.setName(name);
        return this;
    }

    public Course withUniversity(University university) {
        this.setUniversity(university);
        return this;
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

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<TutorCourse> getTutorCourses() {
        return tutorCourses;
    }

    public void setTutorCourses(List<TutorCourse> tutorCourses) {
        this.tutorCourses = tutorCourses;
    }

    public List<TutoringOffer> getTutoringOffers() {
        return tutoringOffers;
    }

    public void setTutoringOffers(List<TutoringOffer> tutoringOffers) {
        this.tutoringOffers = tutoringOffers;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

}
