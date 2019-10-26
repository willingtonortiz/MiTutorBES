package com.mitutor.controllers;

import com.mitutor.converters.CourseConverter;
import com.mitutor.converters.TopicConverter;
import com.mitutor.dtos.TopicDTO;
import com.mitutor.dtos.input.CreateTopicInput;
import com.mitutor.entities.Course;
import com.mitutor.entities.Topic;
import com.mitutor.services.ICourseService;
import com.mitutor.services.ITopicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/topics")
@Api(tags = "Topic", value = "")
public class TopicController {

    @Autowired
    private ITopicService topicService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private TopicConverter topicConverter;
    @Autowired
    private CourseConverter courseConverter;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TopicDTO>> findAll() {
        try {
            List<Topic> topics = topicService.findAll();

            List<TopicDTO> topicsDTO = topics
                    .stream()
                    .map(x -> topicConverter.fromEntity(x))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(topicsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicDTO> findById(
            @PathVariable("id") Integer id
    ) {
        try {
            Optional<Topic> foundCourse = topicService.findById(id);

            if (!foundCourse.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            TopicDTO topicDTO = topicConverter.fromEntity(foundCourse.get());

            return new ResponseEntity<>(topicDTO, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicDTO> create(
            @RequestBody() CreateTopicInput createTopic
    ) {
        try {
            Optional<Course> foundCourse = courseService.findById(createTopic.getCourseId());

            if (!foundCourse.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Topic newTopic = new Topic()
                    .withCourse(foundCourse.get())
                    .withName(createTopic.getName());
            topicService.save(newTopic);

            TopicDTO topicDTO = topicConverter.fromEntity(newTopic);

            return new ResponseEntity<>(topicDTO, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicDTO> delete(
            @PathVariable("id") Integer id
    ) {
        try {

            Optional<Topic> foundTopic = topicService.findById(id);

            if (!foundTopic.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            topicService.deleteById(id);

            TopicDTO topicDTO = topicConverter.fromEntity(foundTopic.get());

            return new ResponseEntity<>(topicDTO, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
