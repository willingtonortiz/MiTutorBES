package com.mitutor.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tutor_course")
public class TutorCourse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public TutorCourse() {
    }

    public TutorCourse withCourse(Course course) {
        this.course = course;
        return this;
    }

    public TutorCourse withTutor(Tutor tutor) {
        this.tutor = tutor;
        return this;
    }

    public TutorCourse(Tutor tutor, Course course) {
        this.tutor = tutor;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
