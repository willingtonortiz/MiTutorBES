package com.mitutor.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mitutor.dtos.Responses.TutoringOfferResponse;
import com.mitutor.entities.Course;
import com.mitutor.entities.TutoringOffer;
import com.mitutor.services.ICourseService;
import com.mitutor.services.ITutoringOfferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.mitutor.converters.UniversityConverter;
import com.mitutor.dtos.UniversityDto;
import com.mitutor.entities.University;
import com.mitutor.services.IUniversityService;

@RestController
@RequestMapping("api/universities")
@Api(tags = "University", value = "Web service RESTfull for universities")
public class UniversitiesController {

    @Autowired
    private IUniversityService universityService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ITutoringOfferService tutoringOfferService;
    @Autowired
    private UniversityConverter universityConverter;

    @ApiOperation(value = "List universities", notes = "Method for listing all universities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Universities found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UniversityDto>> findAll() {
        try {
            List<University> universities = universityService.findAll();

            List<UniversityDto> universitiesDto = universities.stream().map(x -> universityConverter.fromEntity(x))
                    .collect(Collectors.toList());

            return new ResponseEntity<List<UniversityDto>>(universitiesDto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<List<UniversityDto>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{universityId}/courses/{courseId}/tutoringoffers")
    public ResponseEntity<List<TutoringOfferResponse>> findTutoringOffersByUniversityIdAndCourseId(
            @PathVariable("universityId") Integer universityId,
            @PathVariable("courseId") Integer courseId
    ) {
        try {
            Optional<University> foundUniversity = universityService.findById(universityId);
            if (!foundUniversity.isPresent()) {
                return new ResponseEntity<List<TutoringOfferResponse>>(HttpStatus.NOT_FOUND);
            }

            Optional<Course> foundCourse = courseService.findById(courseId);
            if (!foundCourse.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<TutoringOffer> tutoringOffers = tutoringOfferService.findAllByUniversityIdAndCourseId(universityId, courseId);
            List<TutoringOfferResponse> tutoringOffersReponse = new ArrayList<TutoringOfferResponse>();

            for (TutoringOffer item : tutoringOffers) {
                tutoringOffersReponse.add(new TutoringOfferResponse(
                        item.getId(),
                        item.getCourse().getName(),
                        item.getTutor().getPerson().getFullname(),
                        item.getStartTime(),
                        item.getEndTime()
                ));
            }

            return new ResponseEntity<List<TutoringOfferResponse>>(tutoringOffersReponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<List<TutoringOfferResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // public ResponseEntity<T>
}
