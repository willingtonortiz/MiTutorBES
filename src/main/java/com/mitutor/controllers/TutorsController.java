package com.mitutor.controllers;

import com.mitutor.converters.TutorConverter;
import com.mitutor.dtos.TutorDTO;
import com.mitutor.entities.Tutor;
import com.mitutor.services.ITutorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/tutors")
@Api(tags = "Tutor", value = "")
public class TutorsController {

    @Autowired
    private TutorConverter tutorConverter;

    @Autowired
    private ITutorService tutorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TutorDTO>> findAll() {
        try {
            List<Tutor> tutors = tutorService.findAll();

            List<TutorDTO> tutorsDto = tutors
                    .stream()
                    .map(x -> tutorConverter.fromEntity(x))
                    .collect(Collectors.toList());

            return new ResponseEntity<List<TutorDTO>>(tutorsDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<TutorDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
