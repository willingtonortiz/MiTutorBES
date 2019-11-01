package com.mitutor.controllers;

import com.mitutor.converters.TutorConverter;
import com.mitutor.dtos.CourseDTO;
import com.mitutor.dtos.TutorDTO;
import com.mitutor.entities.Course;
import com.mitutor.entities.Tutor;
import com.mitutor.services.ICourseService;
import com.mitutor.services.ITutorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/tutors")
@Api(tags = "Tutor", value = "")
public class TutorsController {

    @Autowired
    private TutorConverter tutorConverter;
    @Autowired
    private ITutorService tutorService;
    @Autowired
    private ICourseService courseService;

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

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(
            value = "/{tutorId}/courses",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> addTutorCourse(
            @PathVariable("tutorId") Integer tutorId
    ) {
        return null;
    }
}
