package com.mitutor.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitutor.dtos.output.QualificationResponse;
import com.mitutor.entities.Person;
import com.mitutor.entities.Qualification;
import com.mitutor.services.IPersonService;
import com.mitutor.services.IQualificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/qualifications")
@Api(tags = "Qualifications", value = "Web service RESTfull for qualifications")
public class QualificationController {

	@Autowired
	private IQualificationService qualificationService;

	@Autowired
	private IPersonService personService;

	@ApiOperation(value = "Register user", notes = "Method get all qualifications by tutor id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Qualifications created successfully"),
			@ApiResponse(code = 404, message = "Person  not found"),
			@ApiResponse(code = 500, message = "Internal server error") })

	@GetMapping(value = "/tutor/{tutorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<QualificationResponse>> findCommentsByTutor(@PathVariable("tutorId") Integer tutorId) {

		try {
			Person found = personService.findById(tutorId).get();

			if (found == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			System.out.print(tutorId);
			List<Qualification> res = qualificationService.findAllCommentsByTutor(found);

			List<QualificationResponse> response = new ArrayList<QualificationResponse>();
			for (int i = 0; i < res.size(); i++) {
				QualificationResponse newComment = new QualificationResponse(res.get(i).getAdresser().getFullname(),
						res.get(i).getComment(), res.get(i).getAdresser().getId());
				response.add(newComment);
			}
			System.out.print(res.size());
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
}
