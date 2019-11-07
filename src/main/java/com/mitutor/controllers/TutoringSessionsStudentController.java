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

import com.mitutor.dtos.Responses.TutoringSessionResponse;
import com.mitutor.dtos.input.CreateTutoringSessionStudent;
import com.mitutor.dtos.output.TutoringSessionStudentResponse;
import com.mitutor.entities.Student;
import com.mitutor.entities.TutoringSession;
import com.mitutor.entities.TutoringSessionsStudent;
import com.mitutor.services.IStudentService;
import com.mitutor.services.ITutoringSessionService;
import com.mitutor.services.ITutoringSessionStudentService;

@RestController
@RequestMapping("api/tutoringSessionStudent")

public class TutoringSessionsStudentController {
	
	
	@Autowired
	private ITutoringSessionStudentService tutoringSessionStudentService;
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private ITutoringSessionService tutoringSessionService;
	
	@PostMapping(
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TutoringSessionStudentResponse> addStudentSession(
				@RequestBody CreateTutoringSessionStudent createTutoringSessionsStudent
			){
		
		
		try {
			Student studentFound =  studentService.findById(createTutoringSessionsStudent.getStudent_id()).get();
			TutoringSession tutoringSessionFound =  tutoringSessionService.findById(createTutoringSessionsStudent.getTutoring_session_id()).get();
			
			if(studentFound == null ) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			if(tutoringSessionFound == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			
			
			TutoringSessionsStudent tutoringSessionStudent =  new TutoringSessionsStudent();
			tutoringSessionStudent.withStudent(studentFound);
			tutoringSessionStudent.withTutoringSession(tutoringSessionFound);
			
			TutoringSessionsStudent newTutoringSessionStudent = tutoringSessionStudentService.save(tutoringSessionStudent);
			TutoringSessionStudentResponse response =  new TutoringSessionStudentResponse();
			
			response.setId(newTutoringSessionStudent.getId());
			response.setStudent_id(newTutoringSessionStudent.getStudent().getId());
			response.setTutoring_session_id(newTutoringSessionStudent.getTutoringSession().getId());
			
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	
	}
	
}
