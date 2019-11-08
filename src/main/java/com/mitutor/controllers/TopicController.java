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

    @ApiOperation(value = "Find all", notes = "Retrieves all topics from database")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Topics retrieved successfully"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
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


    @ApiOperation(value = "Find topic by id", notes = "Find a topic by its id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Topic retrieved successfully"),
            @ApiResponse(code = 404, message = "Topic not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
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


    @ApiOperation(value = "Creates a new topic", notes = "Creates a new topic")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Topic created successfully"),
            @ApiResponse(code = 404, message = "Topic not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
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

            return new ResponseEntity<>(topicDTO, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Deletes a topic", notes = "Deletes a topic by its id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Topic deleted successfully"),
            @ApiResponse(code = 404, message = "Topic not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
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
