package com.mitutor.controllers;

import java.util.Date;

import com.mitutor.entities.*;
import com.mitutor.enums.TutorStatus;
import com.mitutor.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitutor.enums.RoleType;

@RestController
@RequestMapping("api/utils")
public class UtilsController {

    @Autowired
    private IUniversityService universityService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ITopicService topicService;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITutorService tutorService;
    @Autowired
    private ITutoringOfferService tutoringOfferService;
    @Autowired
    private ITutorCourseService tutorCourseService;

    @GetMapping("create")
    public String generateData() throws Exception {

        // Creating university
        University university1 = new University("Universidad Peruana de Ciencias Aplicadas");
        universityService.save(university1);

        // Creating courses
        Course course1 = new Course("calculo 2");
        course1.setUniversity(university1);
        courseService.save(course1);

        Course course2 = new Course("fisica 3");
        course2.setUniversity(university1);
        courseService.save(course2);

        Course course3 = new Course("programacion 1");
        course3.setUniversity(university1);
        courseService.save(course3);

        // Creating topics
        Topic topic1 = new Topic("topic_1_1");
        topic1.setCourse(course1);
        topicService.save(topic1);

        Topic topic2 = new Topic("topic_2_1");
        topic2.setCourse(course2);
        topicService.save(topic2);

        Topic topic3 = new Topic("topic_3_1");
        topic3.setCourse(course3);
        topicService.save(topic3);

        // Creating persons
        Person person1 = new Person("person_1", "lastname_1", 6);
        person1.setUniversity(university1);
        personService.save(person1);

        Person person2 = new Person("person_2", "lastname_2", 5);
        person2.setUniversity(university1);
        personService.save(person2);

        Person person3 = new Person("person_3", "lastname_3", 4);
        person3.setUniversity(university1);
        personService.save(person3);

        // Creating users
        User user1 = new User("username_1", "password_1", "email1@email1.com", RoleType.TUTOR);
        user1.setPerson(person1);
        userService.save(user1);

        User user2 = new User("username_2", "password_2", "email2@email2.com", RoleType.STUDENT);
        user2.setPerson(person2);
        userService.save(user2);

        User user3 = new User("username_3", "password_3", "email3@email3.com", RoleType.STUDENT);
        user3.setPerson(person3);
        userService.save(user3);

        // Creating tutors
        Tutor tutor1 = new Tutor()
                .withDescription("Tutor de calculo")
                .withPoints(3.06)
                .withQualificationCount(50)
                .withStatus(TutorStatus.AVAILABLE)
                .withPerson(person1);
        tutorService.save(tutor1);


        // Creating TutorCourse relashionships
        tutorCourseService.save(new TutorCourse().withCourse(course1).withTutor(tutor1));

        // Creating tutoringOffers
        TutoringOffer tutoringOffer1 = new TutoringOffer()
                .withStartTime(new Date())
                .withEndTime(new Date())
                .withCapacity(5)
                .withDescription("Tutoría de calculo 2")
                .withTutor(tutor1)
                .withCourse(course1)
                .withUniversity(university1)
                .addTopic(topic1)
                .addTopic(topic2)
                .addTopic(topic3);
        tutoringOfferService.save(tutoringOffer1);

        return "Datos creados correctamente";
    }

    @GetMapping("delete")
    public String deleteData() throws Exception {

        // deleting data
        tutoringOfferService.deleteAll();
        tutorCourseService.deleteAll();
        tutorService.deleteAll();
        userService.deleteAll();
        personService.deleteAll();
        topicService.deleteAll();
        courseService.deleteAll();
        universityService.deleteAll();

        return "Datos eliminados";
    }

}
