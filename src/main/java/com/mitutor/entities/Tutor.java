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

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "tutor")
    private List<TutoringOffer> tutoringOffers = new ArrayList<TutoringOffer>();

    @OneToMany(mappedBy = "tutor")
    private List<TutorCourse> tutorCourses = new ArrayList<TutorCourse>();

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

    public Tutor withId(Integer id) {
        this.setId(id);
        return this;
    }

    public Tutor withPoints(Double points) {
        this.setPoints(points);
        return this;
    }

    public Tutor withQualificationCount(Integer qualificationCount) {
        this.setQualificationCount(qualificationCount);
        return this;
    }

    public Tutor withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public Tutor withStatus(String status) {
        this.setStatus(status);
        return this;
    }

    public Tutor withPerson(Person person) {
        this.setPerson(person);
        return this;
    }

    public Tutor addTutoringOffer(TutoringOffer tutoringOffer) {
        this.tutoringOffers.add(tutoringOffer);
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<TutorCourse> getTutorCourses() {
        return tutorCourses;
    }

    public void setTutorCourses(List<TutorCourse> tutorCourses) {
        this.tutorCourses = tutorCourses;
    }
}
