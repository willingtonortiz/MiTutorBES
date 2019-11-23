package com.mitutor.controllers;

import com.mitutor.converters.CourseConverter;
import com.mitutor.converters.TutorConverter;
import com.mitutor.dtos.CourseDTO;
import com.mitutor.dtos.TutorDTO;
import com.mitutor.dtos.output.TutoringOfferInfo;
import com.mitutor.entities.*;
import com.mitutor.services.ICourseService;
import com.mitutor.services.ITutorCourseService;
import com.mitutor.services.ITutorService;
import com.mitutor.services.ITutoringOfferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation(value = "Finds all tutors", notes = "Retrieve all tutors from database")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Tutors retrieved successfully"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
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

    @ApiOperation(value = "Finds all tutoring offers by tutor id", notes = "Retrieve all tutoring offers that a tutor teaches")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Tutors retrieved successfully"),
        @ApiResponse(code = 404, message = "Tutor not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
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


    @ApiOperation(value = "Finds all courses by tutor id", notes = "Retrieve all courses that a tutor teaches")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Courses retrieved successfully"),
        @ApiResponse(code = 404, message = "Tutor not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(
            value = "/{tutorId}/courses",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CourseDTO>> findCoursesByTutorId(
            @PathVariable("tutorId") Integer tutorId
    ) {
        try {

            Optional<Tutor> optionalTutor = tutorService.findById(tutorId);

            if (!optionalTutor.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<Course> courses = courseService.findAllByTutorId(tutorId);

            // courses.forEach(x -> System.out.println((x.getName())));

            List<CourseDTO> coursesDTO = courses
                .stream()
                .map(x -> courseConverter.fromEntity(x))
                .collect(Collectors.toList());

            return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Adds a course to a tutor", notes = "Adds a course to a tutor")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Courses added successfully"),
        @ApiResponse(code = 400, message = "Course already added"),
        @ApiResponse(code = 404, message = "Course not found"),
        @ApiResponse(code = 404, message = "Tutor not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
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
            Optional<Course> foundCourse = courseService.findById(courseId);
            if (!foundCourse.isPresent()) {
                return new ResponseEntity<>(2, HttpStatus.NOT_FOUND);
            }
            
            Optional<Tutor> foundTutor = tutorService.findById(tutorId);
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
            return new ResponseEntity<>(1, HttpStatus.CREATED);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Finds the university of the tutor", notes = "Finds the university of the tutor")
    @ApiResponses({
        @ApiResponse(code = 201, message = "University retrieved successfully"),
        @ApiResponse(code = 404, message = "Tutor not found"),
        @ApiResponse(code = 404, message = "University not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(
            value = "/{tutorId}/university",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> findUniversity(
            @PathVariable("tutorId") Integer tutorId
    ) {
        try {
            Optional<Tutor> foundTutor = tutorService.findById(tutorId);
            if(!foundTutor.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Optional<University> foundUniversity = tutorService.findUniversityByTutorId(tutorId);
            if(!foundUniversity.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            University university = foundUniversity.get();

            return new ResponseEntity<>(university.getId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
