package com.mitutor.converters;

import com.mitutor.dtos.Requests.TutoringOfferRequest;
import com.mitutor.dtos.Requests.TutoringSessionsRequests;
import com.mitutor.entities.TutoringOffer;
import com.mitutor.entities.TutoringSession;
import com.mitutor.services.ICourseService;
import com.mitutor.services.ITutorService;
import com.mitutor.services.IUniversityService;
import com.mitutor.servicesImpls.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TutoringOfferRequestConverter implements IConverter<TutoringOffer, TutoringOfferRequest> {

    @Autowired
    ICourseService courseService;

    @Autowired
    ITutorService tutorService;

    @Autowired
    IUniversityService universityService;

    @Autowired
    TutoringSessionRequestConvert tutoringSessionRequestConvert;


    @Override
    public TutoringOffer fromDto(TutoringOfferRequest dto) {

        TutoringOffer tutoringOffer = new TutoringOffer();
        tutoringOffer.setCapacity(dto.getCapacity());
        tutoringOffer.setDescription(dto.getDescription());

        try {
            tutoringOffer.setCourse(courseService.findById(dto.getCourseId()).get());
            tutoringOffer.setTutor(tutorService.findById(dto.getTutorId()).get());
            tutoringOffer.setUniversity(universityService.findById(dto.getUniversityId()).get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tutoringOffer;

    }


    @Override
    public TutoringOfferRequest fromEntity(TutoringOffer entity) {
        return null;
    }
}
