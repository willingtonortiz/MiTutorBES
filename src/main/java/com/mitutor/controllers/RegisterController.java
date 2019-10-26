package com.mitutor.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	private IUniversityService uniService;

	@Autowired
	private IUserRegisterService userRegService;

	@ApiOperation(value = "Register user", notes = "Method create a new user")
	@ApiResponses({ @ApiResponse(code = 200, message = "User created sucessfully"),
			@ApiResponse(code = 404, message = "University not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRegisterResponse> create(@RequestBody() CreateUserInput createUser) {

		try {
			Optional<University> universityFound = uniService.findById(createUser.getUniversityId());

			if (!universityFound.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			Person newPerson = new Person();
			newPerson.setName(createUser.getName());
			newPerson.setLastName(createUser.getLastName());
			newPerson.setSemester(createUser.getSemester());
			newPerson.setUniversity(universityFound.get());

			Student newStudent = new Student();
			newStudent.setPoints((float) 0.00);
			newStudent.setQualificationCount(0);

			User newUser = new User(createUser.getUsername(), createUser.getPassword(), createUser.getEmail(),
					"student");

			newPerson.setUser(newUser);
			newUser.setPerson(newPerson);

			newPerson.setStudent(newStudent);
			newStudent.setPerson(newPerson);

			User userResult = userRegService.register(newPerson, newStudent, newUser);

			UserRegisterResponse userResponse = new UserRegisterResponse();

			userResponse.setId(userResult.getId());
			userResponse.setEmail(userResult.getEmail());
			userResponse.setUsername(userResult.getUsername());
			userResponse.setPassword(userResult.getPassword());
			userResponse.setRole(userResult.getRole());

			return new ResponseEntity<>(userResponse, HttpStatus.OK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
