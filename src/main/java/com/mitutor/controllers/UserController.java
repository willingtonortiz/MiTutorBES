package com.mitutor.controllers;

import com.mitutor.dtos.UserDTO;
import com.mitutor.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mitutor.dtos.Responses.TutorRegisterResponse;
import com.mitutor.dtos.input.CreateTutorInput;
import com.mitutor.entities.Tutor;
import com.mitutor.services.ITutorService;
import com.mitutor.services.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/users")
@Api(tags = "Users", value = "Web Service RESTFull for user")
public class UserController {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private ITutorService tutorService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAll() {
        try {
            List<User> users = userService.findAll();

            List<UserDTO> usersDto = users
                    .stream()
                    .map(x -> new UserDTO()
                            .withId(x.getId())
                            .withUsername(x.getUsername())
                            .withEmail(x.getEmail())
                            .withPassword(x.getPassword())
                            .withRole(x.getRole()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(usersDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findUserById(@PathVariable("userId") Integer userId) {
        try {
            Optional<User> foundUser = userService.findById(userId);

            if (!foundUser.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            User user = foundUser.get();

            UserDTO userDTO = new UserDTO().withId(user.getId()).withEmail(user.getEmail())
                    .withPassword(user.getPassword()).withUsername(user.getUsername()).withRole(user.getRole());

            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Register tutor", notes = "Method create a new tutor")
    @ApiResponses({@ApiResponse(code = 200, message = "Tutor created sucessfully"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @PostMapping(value = "/subscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<TutorRegisterResponse> subscription(
            @RequestBody() CreateTutorInput createTutorInput
    ) {
        try {
            Tutor newTutor = tutorService.subscription(createTutorInput);
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
