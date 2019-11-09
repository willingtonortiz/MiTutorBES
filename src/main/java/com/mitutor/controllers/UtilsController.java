package com.mitutor.controllers;

import java.util.Date;
import java.util.Optional;

import com.mitutor.entities.*;
import com.mitutor.enums.TutorStatus;
import com.mitutor.services.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private IUserRegisterService userRegService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("create")
    public String generateData() throws Exception {

        // Creating university
        University university1 = new University().withName("Universidad Peruana de Ciencias Aplicadas");
        universityService.save(university1);

        // Creating courses
        Course course1 = new Course().withName("calculo 2").withUniversity(university1);
        courseService.save(course1);
        Course course2 = new Course().withName("fisica 3").withUniversity(university1);
        courseService.save(course2);
        Course course3 = new Course().withName("programacion 1").withUniversity(university1);
        courseService.save(course3);

        // Creating topics
        Topic topic_1_1 = new Topic().withName("topic_1_1").withCourse(course1);
        topicService.save(topic_1_1);
        Topic topic_1_2 = new Topic().withName("topic_1_2").withCourse(course1);
        topicService.save(topic_1_2);
        Topic topic_1_3 = new Topic().withName("topic_1_3").withCourse(course1);
        topicService.save(topic_1_3);

        Topic topic_2_1 = new Topic().withName("topic_2_1").withCourse(course2);
        topicService.save(topic_2_1);
        Topic topic_2_2 = new Topic().withName("topic_2_2").withCourse(course2);
        topicService.save(topic_2_2);
        Topic topic_2_3 = new Topic().withName("topic_2_3").withCourse(course2);
        topicService.save(topic_2_3);

        Topic topic_3_1 = new Topic().withName("topic_3_1").withCourse(course3);
        topicService.save(topic_3_1);
        Topic topic_3_2 = new Topic().withName("topic_3_2").withCourse(course3);
        topicService.save(topic_3_2);
        Topic topic_3_3 = new Topic().withName("topic_3_3").withCourse(course3);
        topicService.save(topic_3_3);


        // Creating persons
        Person person1 = new Person()
                .withName("person_1")
                .withLastname("lastname_1")
                .withSemester(6)
                .withCareer("ingenieria de software")
                .withUniversity(university1);
        personService.save(person1);

        Person person2 = new Person()
                .withName("person_2")
                .withLastname("lastname_2")
                .withSemester(5)
                .withCareer("ingenieria industrial")
                .withUniversity(university1);
        personService.save(person2);

        Person person3 = new Person()
                .withName("person_3")
                .withLastname("lastname_3")
                .withSemester(4)
                .withCareer("ingenieria economica")
                .withUniversity(university1);
        personService.save(person3);

        // Creating users
        User user1 = new User()
                .withUsername("username_1")
                .withPassword(passwordEncoder.encode("password_1"))
                .withEmail("email1@email1.com")
                .withRole(RoleType.TUTOR)
                .withPerson(person1);
        userService.save(user1);

        User user2 = new User()
                .withUsername("username_2")
                .withPassword(passwordEncoder.encode("password_2"))
                .withEmail("email2@email2.com")
                .withRole(RoleType.TUTOR)
                .withPerson(person2);
        userService.save(user2);

        User user3 = new User()
                .withUsername("username_3")
                .withPassword(passwordEncoder.encode("password_3"))
                .withEmail("email3@email3.com")
                .withRole(RoleType.TUTOR)
                .withPerson(person3);
        userService.save(user3);

        // Creating tutors
        Tutor tutor1 = new Tutor()
                .withDescription("Tutor de calculo")
                .withPoints(3.06)
                .withQualificationCount(50)
                .withStatus(TutorStatus.AVAILABLE)
                .withPerson(person1);
        tutorService.save(tutor1);

        Tutor tutor3 = new Tutor()
                .withDescription("Tutor de programacion")
                .withPoints(4.05)
                .withQualificationCount(25)
                .withStatus(TutorStatus.AVAILABLE)
                .withPerson(person3);
        tutorService.save(tutor3);


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
                .addTopic(topic_1_1)
                .addTopic(topic_1_2)
                .addTopic(topic_1_3);
        tutoringOfferService.save(tutoringOffer1);

        TutoringOffer tutoringOffer2 = new TutoringOffer()
                .withStartTime(new Date())
                .withEndTime(new Date())
                .withCapacity(4)
                .withDescription("Tutoria de programacion 1")
                .withTutor(tutor3)
                .withCourse(course3)
                .withUniversity(university1)
                .addTopic(topic_3_1)
                .addTopic(topic_3_2)
                .addTopic(topic_3_3);
        tutoringOfferService.save(tutoringOffer2);

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


    @ApiOperation(value = "Find user by username", notes = "Method for validate username if is available or not")
    @ApiResponses({@ApiResponse(code = 302, message = "Username is not available"),
            @ApiResponse(code = 404, message = "Username is available "),
            @ApiResponse(code = 500, message = "Internal server error")})

    @GetMapping("userExist/{username}")
    public ResponseEntity<String> isUsernameExist(@PathVariable("username") String username) {
        try {
            Optional<User> userFound = userRegService.findyByUsername(username);

            if (!userFound.isPresent()) {
                return new ResponseEntity<String>("User is available", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<String>("User is not available", HttpStatus.FOUND);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Find user by email", notes = "Method for validate email if is available or not")
    @ApiResponses({@ApiResponse(code = 302, message = "Email is not available"),
            @ApiResponse(code = 404, message = "Email is available "),
            @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping("emailExist/{email}")
    public ResponseEntity<String> isEmailExist(@PathVariable("email") String email) {

        try {
            Optional<User> userFound = userRegService.findByEmail(email);
            if (!userFound.isPresent()) {
                return new ResponseEntity<String>("El email esta disponible", HttpStatus.NOT_FOUND);

            }
            return new ResponseEntity<String>("El email ya existe", HttpStatus.FOUND);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
