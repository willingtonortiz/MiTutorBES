package com.mitutor.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.mitutor.converters.UniversityConverter;
import com.mitutor.dtos.UniversityDto;
import com.mitutor.entities.University;
import com.mitutor.services.IUniversityService;

@RestController
@RequestMapping("api/university")
public class UniversityController {

	@Autowired
	private IUniversityService universityService;

	@Autowired
	private UniversityConverter universityConverter;

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
	
	// public ResponseEntity<T>
}
