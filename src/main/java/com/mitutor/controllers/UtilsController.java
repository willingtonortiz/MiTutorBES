package com.mitutor.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    @Autowired
    private ITutoringSessionService tutoringSessionService;

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
        Topic topic_1_1 = new Topic().withName("limites").withCourse(course1);
        topicService.save(topic_1_1);
        Topic topic_1_2 = new Topic().withName("derivadas").withCourse(course1);
        topicService.save(topic_1_2);
        Topic topic_1_3 = new Topic().withName("integrales").withCourse(course1);
        topicService.save(topic_1_3);

        Topic topic_2_1 = new Topic().withName("MRU").withCourse(course2);
        topicService.save(topic_2_1);
        Topic topic_2_2 = new Topic().withName("MRUV").withCourse(course2);
        topicService.save(topic_2_2);
        Topic topic_2_3 = new Topic().withName("Movimiento parabolico").withCourse(course2);
        topicService.save(topic_2_3);

        Topic topic_3_1 = new Topic().withName("punteros").withCourse(course3);
        topicService.save(topic_3_1);
        Topic topic_3_2 = new Topic().withName("matrices").withCourse(course3);
        topicService.save(topic_3_2);
        Topic topic_3_3 = new Topic().withName("sentencias condicionales").withCourse(course3);
        topicService.save(topic_3_3);


        // Creating persons
        Person person1 = new Person()
                .withName("Kevin")
                .withLastname("Mitchell")
                .withSemester(6)
                .withCareer("matematica pura")
                .withUniversity(university1);
        personService.save(person1);

        Person person2 = new Person()
                .withName("Elaine")
                .withLastname("Sims")
                .withSemester(5)
                .withCareer("ingeniería de sistemas")
                .withUniversity(university1);
        personService.save(person2);

        Person person3 = new Person()
                .withName("Alexander")
                .withLastname("Eastman")
                .withSemester(4)
                .withCareer("ingeniería de software")
                .withUniversity(university1);
        personService.save(person3);

        // Creating users
        User user1 = new User()
                .withUsername("Kevin")
                .withPassword(passwordEncoder.encode("Mitchell"))
                .withEmail("KevinJMitchell@gustr.com")
                .withRole(RoleType.TUTOR)
                .withPerson(person1);
        userService.save(user1);

        User user2 = new User()
                .withUsername("Elaine")
                .withPassword(passwordEncoder.encode("Sims"))
                .withEmail("ElaineDSims@gustr.com")
                .withRole(RoleType.TUTOR)
                .withPerson(person2);
        userService.save(user2);

        User user3 = new User()
                .withUsername("Alexander")
                .withPassword(passwordEncoder.encode("Eastman"))
                .withEmail("AlexanderSEastman@gustr.com")
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

        Tutor tutor2 = new Tutor()
                .withDescription("Tutor de fisica")
                .withPoints(4.12)
                .withQualificationCount(35)
                .withStatus(TutorStatus.AVAILABLE)
                .withPerson(person2);
        tutorService.save(tutor2);

        Tutor tutor3 = new Tutor()
                .withDescription("Tutor de programacion")
                .withPoints(4.75)
                .withQualificationCount(75)
                .withStatus(TutorStatus.AVAILABLE)
                .withPerson(person3);
        tutorService.save(tutor3);


        // Creating TutorCourse relashionships
        tutorCourseService.save(new TutorCourse().withCourse(course1).withTutor(tutor1));
        tutorCourseService.save(new TutorCourse().withCourse(course2).withTutor(tutor2));
        tutorCourseService.save(new TutorCourse().withCourse(course3).withTutor(tutor3));

        // Creating tutoringOffers
        TutoringOffer tutoringOffer1 = new TutoringOffer()
                .withStartTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 19).getTime())
                .withEndTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 21).getTime())
                .withCapacity(5)
                .withDescription("Tutoría de calculo 2")
                .withTutor(tutor1)
                .withCourse(course1)
                .withUniversity(university1)
                .addTopic(topic_1_1)
                .addTopic(topic_1_2)
                .addTopic(topic_1_3);
        tutoringOfferService.save(tutoringOffer1);

        TutoringSession tutoringSession_1_1 = new TutoringSession()
                .withTutoringOffer(tutoringOffer1)
                .withStartTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 19, 16,0,0).getTime())
                .withEndTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 19, 18,0,0).getTime())
                .withDescription("Primer repaso de cálculo")
                .withPlace("UPC Villa cubículo 23")
                .withPrice(20f)
                .withStudentCount(0)
                .addTopic(topic_1_1);
        tutoringSessionService.save(tutoringSession_1_1);

        TutoringSession tutoringSession_1_2 = new TutoringSession()
                .withTutoringOffer(tutoringOffer1)
                .withStartTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 20, 16,0,0).getTime())
                .withEndTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 20, 18,0,0).getTime())
                .withDescription("Segundo repaso de cálculo")
                .withPlace("UPC Villa cubículo 25")
                .withPrice(20f)
                .withStudentCount(0)
                .addTopic(topic_1_2);
        tutoringSessionService.save(tutoringSession_1_2);

        TutoringSession tutoringSession_1_3 = new TutoringSession()
                .withTutoringOffer(tutoringOffer1)
                .withStartTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 21, 16,0,0).getTime())
                .withEndTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 21, 18,0,0).getTime())
                .withDescription("Último repaso de cálculo")
                .withPlace("UPC Villa cubículo 30")
                .withPrice(20f)
                .withStudentCount(0)
                .addTopic(topic_1_3);
        tutoringSessionService.save(tutoringSession_1_3);


        TutoringOffer tutoringOffer2 = new TutoringOffer()
                .withStartTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 19).getTime())
                .withEndTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 21).getTime())
                .withCapacity(5)
                .withDescription("Tutoria de fisica")
                .withTutor(tutor2)
                .withCourse(course2)
                .withUniversity(university1)
                .addTopic(topic_2_1)
                .addTopic(topic_2_2)
                .addTopic(topic_2_3);
        tutoringOfferService.save(tutoringOffer2);

        TutoringSession tutoringSession_2_1 = new TutoringSession()
                .withTutoringOffer(tutoringOffer2)
                .withStartTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 19, 10,0,0).getTime())
                .withEndTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 19, 12,0,0).getTime())
                .withDescription("Primer repaso de fisica")
                .withPlace("UPC Villa cubículo 02")
                .withPrice(20f)
                .withStudentCount(0)
                .addTopic(topic_2_1);
        tutoringSessionService.save(tutoringSession_2_1);

        TutoringSession tutoringSession_2_2 = new TutoringSession()
                .withTutoringOffer(tutoringOffer2)
                .withStartTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 20, 10,0,0).getTime())
                .withEndTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 20, 12,0,0).getTime())
                .withDescription("Repaso final")
                .withPlace("UPC Villa cubículo 07")
                .withPrice(20f)
                .withStudentCount(0)
                .addTopic(topic_2_2)
                .addTopic(topic_2_3);
        tutoringSessionService.save(tutoringSession_2_2);


        TutoringOffer tutoringOffer3 = new TutoringOffer()
                .withStartTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 19).getTime())
                .withEndTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 21).getTime())
                .withCapacity(5)
                .withDescription("Preparación para el examen final O_O")
                .withTutor(tutor3)
                .withCourse(course3)
                .withUniversity(university1)
                .addTopic(topic_3_1)
                .addTopic(topic_3_2)
                .addTopic(topic_3_3);
        tutoringOfferService.save(tutoringOffer3);

        TutoringSession tutoringSession_3_1 = new TutoringSession()
                .withTutoringOffer(tutoringOffer3)
                .withStartTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 19, 15,0,0).getTime())
                .withEndTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 19, 17,0,0).getTime())
                .withDescription("Primer repaso de programación 1")
                .withPlace("UPC Villa cubículo 11")
                .withPrice(20f)
                .withStudentCount(0)
                .addTopic(topic_3_1);
        tutoringSessionService.save(tutoringSession_3_1);

        TutoringSession tutoringSession_3_2 = new TutoringSession()
                .withTutoringOffer(tutoringOffer3)
                .withStartTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 20, 15,0,0).getTime())
                .withEndTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 20, 17,0,0).getTime())
                .withDescription("Segundo repaso de programación 1")
                .withPlace("UPC Villa cubículo 12")
                .withPrice(20f)
                .withStudentCount(0)
                .addTopic(topic_3_2);
        tutoringSessionService.save(tutoringSession_3_2);

        TutoringSession tutoringSession_3_3 = new TutoringSession()
                .withTutoringOffer(tutoringOffer3)
                .withStartTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 21, 15,0,0).getTime())
                .withEndTime(new GregorianCalendar(2019, Calendar.NOVEMBER, 21, 17,0,0).getTime())
                .withDescription("Último repaso de programacion 1")
                .withPlace("UPC Villa cubículo 13")
                .withPrice(20f)
                .withStudentCount(0)
                .addTopic(topic_3_3);
        tutoringSessionService.save(tutoringSession_3_3);

        return "Datos creados correctamente";
    }

    @GetMapping("delete")
    public String deleteData() throws Exception {

        // deleting data
        tutoringSessionService.deleteAll();
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
