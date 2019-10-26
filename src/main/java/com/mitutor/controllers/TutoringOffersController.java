package com.mitutor.controllers;

import com.mitutor.converters.TutoringOfferRequestConverter;
import com.mitutor.converters.TutoringOfferResponseConverter;
import com.mitutor.converters.TutoringSessionRequestConvert;
import com.mitutor.converters.TutoringSessionResponseConverter;
import com.mitutor.dtos.Requests.TutoringOfferRequest;
import com.mitutor.dtos.Requests.TutoringSessionsRequests;
import com.mitutor.dtos.Responses.TutoringOfferResponse;
import com.mitutor.entities.Topic;
import com.mitutor.entities.TutoringOffer;
import com.mitutor.entities.TutoringSession;
import com.mitutor.services.ITopicService;
import com.mitutor.services.ITutoringOfferService;
import com.mitutor.services.ITutoringSessionService;
import com.mitutor.utils.UtilServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;


@RestController
@RequestMapping("/api/tutoringoffers")
@Api(tags = "TutoringOffers", value = "Servicio Web RESTFull de TutoringOffers")
public class TutoringOffersController {


    @Autowired
    TutoringOfferRequestConverter tutoringOfferRequestConverter;

    @Autowired
    TutoringSessionRequestConvert tutoringSessionRequestConverter;


    @Autowired
    TutoringOfferResponseConverter tutoringOfferResponseConverter;

    @Autowired
    TutoringSessionResponseConverter tutoringSessionResponseConverter;

    @Autowired
    ITutoringOfferService tutoringOfferService;

    @Autowired
    ITutoringSessionService tutoringSessionService;

    @Autowired
    ITopicService topicService;


    @GetMapping(value = "searchById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar ofertas por Id", notes = "Método para buscar ofertas por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Oferta encontrada"),
            @ApiResponse(code = 404, message = "Oferta no encontrada")
    })
    public ResponseEntity<TutoringOfferResponse> findById(@PathVariable("id") Integer id) {
        try {
            Optional<TutoringOffer> tutoringOffer = tutoringOfferService.findById(id);

            if (!tutoringOffer.isPresent()) {
                return new ResponseEntity<TutoringOfferResponse>(HttpStatus.NOT_FOUND);
            } else {
                TutoringOfferResponse tutoringOfferResponse = tutoringOfferResponseConverter.fromEntity(tutoringOffer.get());
                return new ResponseEntity<TutoringOfferResponse>(tutoringOfferResponse, HttpStatus.OK);
            }

        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<TutoringOfferResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Crear ofertas", notes = "Método para crear ofertas")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Oferta creada"),
            @ApiResponse(code = 400, message = "Solicitud de creacion invalida")
    })
    public ResponseEntity<TutoringOfferRequest> insertTutoringOffer(@Valid @RequestBody TutoringOfferRequest tutoringOfferRequest) {

        try {

            TutoringOffer tutoringOffer = tutoringOfferRequestConverter.fromDto(tutoringOfferRequest);
            List<Date> startDates = new ArrayList<>();
            List<Date> endDates = new ArrayList<>();

            for (TutoringSessionsRequests s : tutoringOfferRequest.getTutoringSessions()) {
                startDates.add(s.getStartTime());
                endDates.add(s.getEndTime());
            }

            tutoringOffer.setStartTime(UtilServices.getMaxDate(startDates));
            tutoringOffer.setEndTime(UtilServices.getMinDate(endDates));

            tutoringOffer = tutoringOfferService.save(tutoringOffer);

            TutoringSession tutoringSession = null;
            Topic topic = null;

            HashSet<Topic> topics = new HashSet<>();

            for (TutoringSessionsRequests t : tutoringOfferRequest.getTutoringSessions()) {
                tutoringSession = tutoringSessionRequestConverter.fromDto(t);
                tutoringSession = tutoringSessionService.save(tutoringSession);
                tutoringSession.setTutoringOffer(tutoringOffer);


                for (Topic to : tutoringSession.getTopics()) {
                    to.getTutoringSessions().add(tutoringSession);
                    topicService.save(to);
                    topics.add(to);
                }

                tutoringSessionService.save(tutoringSession);
                tutoringOffer.getTutoringSessions().add(tutoringSession);
            }

            tutoringOffer = tutoringOfferService.save(tutoringOffer);

            for (Topic to : topics) {
                to.getTutoringOffers().add(tutoringOffer);
                topicService.save(to);
            }

            tutoringOfferService.save(tutoringOffer);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(tutoringOffer.getId()).toUri();

            return ResponseEntity.created(location).build();

        } catch (Exception ex) {
            return new ResponseEntity<TutoringOfferRequest>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


}
