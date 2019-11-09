package com.mitutor.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mitutor.converters.CourseConverter;
import com.mitutor.dtos.output.TutorInfo;
import com.mitutor.dtos.output.TutoringOfferInfo;
import com.mitutor.dtos.CourseDTO;
import com.mitutor.entities.Course;
import com.mitutor.entities.Tutor;
import com.mitutor.entities.TutoringOffer;
import com.mitutor.services.ICourseService;
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

import com.mitutor.converters.UniversityConverter;
import com.mitutor.dtos.UniversityDTO;
import com.mitutor.entities.University;
import com.mitutor.services.IUniversityService;

@RestController
@RequestMapping("api/universities")
@Api(tags = "University", value = "Web service RESTfull for universities")
public class UniversitiesController {

    //region Attributes
    @Autowired
    private IUniversityService universityService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ITutoringOfferService tutoringOfferService;
    @Autowired
    private ITutorService tutorService;
    @Autowired
    private UniversityConverter universityConverter;
    @Autowired
    private CourseConverter courseConverter;
    //endregion


    //region FindAll
    @ApiOperation(value = "List universities", notes = "Method for listing all universities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Universities found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UniversityDTO>> findAll() {
        try {
            List<University> universities = universityService.findAll();

            List<UniversityDTO> universitiesDto = universities
                    .stream()
                    .map(x -> universityConverter.fromEntity(x))
                    .collect(Collectors.toList());

            return new ResponseEntity<List<UniversityDTO>>(universitiesDto, HttpStatus.OK);
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            return new ResponseEntity<List<UniversityDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion


    //region FindTutoringOffersByUniversityIdAndCourseId
    @ApiOperation(value = "List tutoring offers by university and course", notes = "Method for listing all tutoring offers related to an university and course")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tutoring offers found"),
            @ApiResponse(code = 404, message = "University or Course not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(value = "/{universityId}/courses/{courseId}/tutoringoffers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TutoringOfferInfo>> findTutoringOffersByUniversityIdAndCourseId(
            @PathVariable("universityId") Integer universityId,
            @PathVariable("courseId") Integer courseId
    ) {
        try {
            Optional<University> foundUniversity = universityService.findById(universityId);
            if (!foundUniversity.isPresent()) {
                return new ResponseEntity<List<TutoringOfferInfo>>(HttpStatus.NOT_FOUND);
            }

            Optional<Course> foundCourse = courseService.findById(courseId);
            if (!foundCourse.isPresent()) {
                return new ResponseEntity<List<TutoringOfferInfo>>(HttpStatus.NOT_FOUND);
            }

            List<TutoringOffer> tutoringOffers = tutoringOfferService
                    .findAllByUniversityIdAndCourseId(universityId, courseId);

            List<TutoringOfferInfo> tutoringOffersInfo = tutoringOffers
                    .stream()
                    .map(x -> new TutoringOfferInfo()
                            .withId(x.getId())
                            .withCourseName(x.getCourse().getName())
                            .withTutorName(x.getTutor().getPerson().getFullname())
                            .withStartTime(x.getStartTime())
                            .withEndTime(x.getEndTime())
                    )
                    .collect(Collectors.toList());

            return new ResponseEntity<List<TutoringOfferInfo>>(tutoringOffersInfo, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<List<TutoringOfferInfo>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion


    //region FindCoursesByUniversityId
    @ApiOperation(value = "List courses by university id", notes = "Method for listing all courses related to an university")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Courses found"),
            @ApiResponse(code = 404, message = "Courses not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(value = "/{universityId}/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseDTO>> findCoursesByUniversityId(
            @PathVariable("universityId") Integer universityId,
            @RequestParam(value = "courseName", required = false) String courseName
    ) {
        try {
            if (courseName == null) {
                List<Course> courses = courseService
                        .findAllByUniversityId(universityId);

                List<CourseDTO> coursesDTO = courses
                        .stream()
                        .map(x -> courseConverter.fromEntity(x))
                        .collect(Collectors.toList());

                return new ResponseEntity<List<CourseDTO>>(coursesDTO, HttpStatus.OK);
            } else {
                Optional<Course> optionalCourse = courseService
                        .findByUniversityIdAndName(universityId, courseName);

                if (!optionalCourse.isPresent()) {
                    return new ResponseEntity<List<CourseDTO>>(HttpStatus.BAD_REQUEST);
                }

                Course course = optionalCourse.get();

                List<CourseDTO> coursesDTO = new ArrayList<CourseDTO>();
                coursesDTO.add(courseConverter.fromEntity(course));

                return new ResponseEntity<List<CourseDTO>>(coursesDTO, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<List<CourseDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion


    //region FindTutorsByUniversityIdAndCourseId
    @ApiOperation(value = "Find tutors by university id and course id", notes = "Method for listing all tutors related to an university and a specific course")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tutors found"),
            @ApiResponse(code = 400, message = "University or course not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(value = "/{universityId}/courses/{courseId}/tutors")
    public ResponseEntity<List<TutorInfo>> findTutorsByUniversityIdAndCourseId(
            @PathVariable("universityId") Integer universityId,
            @PathVariable("courseId") Integer courseId
    ) {
        try {
            Optional<University> foundUniversity = universityService.findById(universityId);
            Optional<Course> foundCourse = courseService.findById(courseId);

            if (!foundUniversity.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if (!foundCourse.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<Tutor> tutors = tutorService.findAllByUniversityIdAndCourseId(universityId, courseId);

            List<TutorInfo> tutorsDTO = tutors
                    .stream()
                    .map(x -> new TutorInfo()
                            .withId(x.getId())
                            .withFullName(x.getPerson().getFullname())
                            .withCareer(x.getPerson().getCareer())
                            .withPoints(x.getPoints())
                    )
                    .collect(Collectors.toList());

            return new ResponseEntity<>(tutorsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion
}
