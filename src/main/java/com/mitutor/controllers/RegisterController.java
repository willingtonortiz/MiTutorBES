package com.mitutor.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitutor.dtos.Responses.UserRegisterResponse;
import com.mitutor.dtos.input.CreateUserInput;
import com.mitutor.entities.Person;
import com.mitutor.entities.Student;
import com.mitutor.entities.University;
import com.mitutor.entities.User;
import com.mitutor.services.IUniversityService;
import com.mitutor.services.IUserRegisterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/register")
@Api(tags = "Register", value = "Web Service RESTfull for register")
public class RegisterController {

    @Autowired
    private IUniversityService universityService;
    @Autowired
    private IUserRegisterService userRegisterService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "Register user", notes = "Method create a new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User created successfully"),
            @ApiResponse(code = 404, message = "University not found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserRegisterResponse> register(
            @RequestBody() CreateUserInput createUser
    ) {
        try {
            Optional<University> foundUniversity = universityService.findById(createUser.getUniversityId());

            if (!foundUniversity.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Person newPerson = new Person()
                    .withName(createUser.getName())
                    .withLastname(createUser.getLastName())
                    .withSemester(createUser.getSemester())
                    .withCareer(createUser.getCareer())
                    .withUniversity(foundUniversity.get());

            Student newStudent = new Student()
                    .withPoints(0.00f)
                    .withQualificationCount(0);

            // Encoding password
            String encodedPassword = passwordEncoder.encode(createUser.getPassword());

            User newUser = new User()
                    .withUsername(createUser.getUsername())
                    .withPassword(encodedPassword)
                    .withEmail(createUser.getEmail())
                    .withRole("STUDENT");

            newPerson.setUser(newUser);
            newUser.setPerson(newPerson);

            newPerson.setStudent(newStudent);
            newStudent.setPerson(newPerson);

            User userResult = userRegisterService.register(newPerson, newStudent, newUser);

            UserRegisterResponse userResponse = new UserRegisterResponse()
                    .withId(userResult.getId())
                    .withEmail(userResult.getEmail())
                    .withUsername(userResult.getUsername())
                    .withRole(userResult.getRole());

            return new ResponseEntity<>(userResponse, HttpStatus.OK);

        } catch (Exception e) {

//            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
