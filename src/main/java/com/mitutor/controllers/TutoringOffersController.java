package com.mitutor.controllers;

import com.mitutor.dtos.Requests.TutoringOfferRequest;
import com.mitutor.entities.TutoringOffer;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tutoringoffers")
@Api(tags = "TutoringOffers", value ="Servicio Web RESTFull de TutoringOffers")
public class TutoringOffersController {


   /* @PostMapping(value = "{tutorId}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Crear TutoringOffers", notes ="Método para crear ofertas")
    @ApiResponses({
            @ApiResponse(code=201,message = "Ofertas creada"),
            @ApiResponse(code=400,message = "Solicitud de creacion invalida")
    })
    public ResponseEntity<TutoringOfferRequest> insertTutoringOffer(@PathVariable("tutorId") int id, @Valid @RequestBody TutoringOfferRequest tutoringOfferRequest){


        try {
            TutoringOffer tutoringOffer =
            Reserva reservaNew = new Reserva();
            Cliente cliente = clienteService.findById(id).get();
            reserva.setCliente(cliente);

            reservaNew = reservaService.save(reserva);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(reservaNew.getId()).toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<Reserva>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        *



    }
    */


}
