package com.mitutor.controllers;

import com.mitutor.converters.CourseConverter;
import com.mitutor.converters.TutorConverter;
import com.mitutor.converters.TutoringOfferConverter;
import com.mitutor.dtos.CourseDTO;
import com.mitutor.dtos.TutorDTO;
import com.mitutor.dtos.TutoringOfferDTO;
import com.mitutor.dtos.output.TutoringOfferInfo;
import com.mitutor.entities.*;
import com.mitutor.services.ICourseService;
import com.mitutor.services.ITutorCourseService;
import com.mitutor.services.ITutorService;
import com.mitutor.services.ITutoringOfferService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/tutors")
@Api(tags = "Tutor", value = "")
public class TutorsController {

    @Autowired
    private ITutorService tutorService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ITutorCourseService tutorCourseService;
    @Autowired
    private ITutoringOfferService tutoringOfferService;
    @Autowired
    private TutorConverter tutorConverter;
    @Autowired
    private CourseConverter courseConverter;
    @Autowired
    private TutoringOfferConverter tutoringOfferConverter;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TutorDTO>> findAll() {
        try {
            List<Tutor> tutors = tutorService.findAll();

            List<TutorDTO> tutorsDto = tutors
                    .stream()
                    .map(x -> tutorConverter.fromEntity(x))
                    .collect(Collectors.toList());

            return new ResponseEntity<List<TutorDTO>>(tutorsDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<TutorDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "{tutorId}/tutoringoffers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TutoringOfferInfo>> findTutoringOffersByTutorId(@PathVariable("tutorId") Integer tutorId) {
        try {
            Optional<Tutor> optionalTutor = tutorService.findById(tutorId);

            if (!optionalTutor.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<TutoringOffer> tutoringOffers = tutoringOfferService.findAllByTutorId(tutorId);

            List<TutoringOfferInfo> tutoringOffersInfo = tutoringOffers
                    .stream()
                    .map(x -> new TutoringOfferInfo().withCourseName(x.getCourse().getName())
                            .withId(x.getId()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(tutoringOffersInfo);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(
            value = "/{tutorId}/courses",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CourseDTO>> findCoursesByTutorId(
            @PathVariable("tutorId") Integer tutorId
    ) {
        try {
            List<Course> courses = courseService.findAllByTutorId(tutorId);

            courses.forEach(x -> System.out.println((x.getName())));

            List<CourseDTO> coursesDTO = courses.stream().map(x -> courseConverter.fromEntity(x)).collect(Collectors.toList());

            return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(
            value = "/{tutorId}/courses",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> addTutorCourse(
            @PathVariable("tutorId") Integer tutorId,
            @RequestBody() Integer courseId
    ) {
        try {
            // System.out.println(tutorId + " " + courseId);
            Optional<Course> foundCourse = courseService.findById(courseId);
            Optional<Tutor> foundTutor = tutorService.findById(tutorId);

            if (!foundCourse.isPresent()) {
                return new ResponseEntity<>(2, HttpStatus.NOT_FOUND);
            }
            if (!foundTutor.isPresent()) {
                return new ResponseEntity<>(3, HttpStatus.NOT_FOUND);
            }

            Course course = foundCourse.get();
            Tutor tutor = foundTutor.get();

            List<Course> courses = courseService.findAllByTutorId(tutor.getId());

            for (Course item : courses) {
                if (item.getId().equals(course.getId()))
                    return new ResponseEntity<>(4, HttpStatus.BAD_REQUEST);
            }

            tutorCourseService.save(new TutorCourse().withTutor(tutor).withCourse(course));
            return new ResponseEntity<>(1, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping(
            value = "/{tutorId}/university",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> findUniversity(
            @PathVariable("tutorId") Integer tutorId
    ) {
        try {
            University university = tutorService.findUniversity(tutorId);
            return new ResponseEntity<>(university.getId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
