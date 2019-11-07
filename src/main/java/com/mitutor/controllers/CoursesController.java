package com.mitutor.controllers;

import com.mitutor.converters.CourseConverter;
import com.mitutor.converters.TopicConverter;
import com.mitutor.converters.UniversityConverter;
import com.mitutor.dtos.CourseDTO;
import com.mitutor.dtos.TopicDTO;
import com.mitutor.dtos.input.CreateCourseInput;
import com.mitutor.entities.Course;
import com.mitutor.entities.Topic;
import com.mitutor.entities.University;
import com.mitutor.services.ICourseService;
import com.mitutor.services.IUniversityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/courses")
@Api(tags = "Course", value = "")
public class CoursesController {
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IUniversityService universityService;
    @Autowired
    private CourseConverter courseConverter;
    @Autowired
    private UniversityConverter universityConverter;
    @Autowired
    private TopicConverter topicConverter;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseDTO>> findAll() {
        try {
            List<Course> courses = courseService.findAll();

            List<CourseDTO> coursesDTO = courses
                    .stream()
                    .map(x -> courseConverter.fromEntity(x))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> findById(
            @PathVariable("id") Integer id
    ) {
        try {
            Optional<Course> foundCourse = courseService.findById(id);

            if (!foundCourse.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            CourseDTO courseDTO = courseConverter.fromEntity(foundCourse.get());

            return new ResponseEntity<>(courseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> create(
            @RequestBody() CreateCourseInput createCourse
    ) {
        try {
            Optional<University> optionalUniversity = universityService
                    .findById(createCourse.getUniversityId());

            if (!optionalUniversity.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Course newCourse = new Course()
                    .withName(createCourse.getName())
                    .withUniversity(optionalUniversity.get());

            newCourse = courseService.save(newCourse);

            CourseDTO courseDTO = courseConverter.fromEntity(newCourse);

            return new ResponseEntity<>(courseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> delete(
            @PathVariable("id") Integer id
    ) {
        try {
            Optional<Course> foundCourse = courseService.findById(id);

            if (!foundCourse.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            courseService.deleteById(id);

            CourseDTO courseDTO = courseConverter.fromEntity(foundCourse.get());

            return new ResponseEntity<>(courseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping(value = "/{id}/topics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TopicDTO>> findTopic(
            @PathVariable("id") Integer id
    ) {
        try {
            List<Topic> foundTopics = courseService.findTopics(id);

            if (foundTopics == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<TopicDTO> topicsDTO = new ArrayList<>();

            for(int i =0; i<foundTopics.size();++i){
                topicsDTO.add(topicConverter.fromEntity(foundTopics.get(i)));
            }

            return new ResponseEntity<>(topicsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
