package com.mitutor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitutor.dtos.Responses.TutorRegisterResponse;
import com.mitutor.dtos.input.CreateTutorInput;
import com.mitutor.entities.Tutor;
import com.mitutor.services.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("api/users")
@Api(tags = "Users", value = "Web Service RESTFull for user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "Register tutor", notes = "Method create a new tutor")
    @ApiResponses({@ApiResponse(code = 200, message = "Tutor created sucessfully"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @PostMapping(value = "/subscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<TutorRegisterResponse> subscription(
            @RequestBody() CreateTutorInput createTutorInput
    ) {
        try {
            Tutor newTutor = userService.subscription(createTutorInput);
            if (newTutor == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            TutorRegisterResponse tutorResponse = new TutorRegisterResponse();

            tutorResponse.setTutorId(newTutor.getId());
            tutorResponse.setQualificationCount(newTutor.getQualificationCount());
            tutorResponse.setDescription(newTutor.getDescription());
            tutorResponse.setPoints(newTutor.getPoints());
            tutorResponse.setStatus(newTutor.getStatus());


            return new ResponseEntity<>(tutorResponse, HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
