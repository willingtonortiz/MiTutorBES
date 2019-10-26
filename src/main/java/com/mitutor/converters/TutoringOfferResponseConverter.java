package com.mitutor.converters;

import com.mitutor.dtos.Responses.TutoringOfferResponse;
import com.mitutor.entities.Topic;
import com.mitutor.entities.TutoringOffer;
import com.mitutor.entities.TutoringSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TutoringOfferResponseConverter implements IConverter<TutoringOffer, TutoringOfferResponse> {


    @Autowired
    TutoringSessionResponseConverter tutoringSessionResponseConverter;


    @Override
    public TutoringOffer fromDto(TutoringOfferResponse dto) {
        return null;
    }

    @Override
    public TutoringOfferResponse fromEntity(TutoringOffer entity) {

        TutoringOfferResponse tutoringOfferResponse = new TutoringOfferResponse();
        tutoringOfferResponse.setCapacity(entity.getCapacity());
        tutoringOfferResponse.setDescription(entity.getDescription());
        tutoringOfferResponse.setEndTime(entity.getEndTime());
        tutoringOfferResponse.setStartTime(entity.getStartTime());
        tutoringOfferResponse.setTutoringOfferId(entity.getId());
        tutoringOfferResponse.setTutor(entity.getTutor().getPerson().getName());
        tutoringOfferResponse.setCourse(entity.getCourse().getName());
        tutoringOfferResponse.setUniversityName(entity.getUniversity().getName());


        for(Topic topics: entity.getTopics())
            tutoringOfferResponse.getTopicsName().add(topics.getName());

        for(TutoringSession t : entity.getTutoringSessions())
            tutoringOfferResponse.getTutoringSessionResponses().add(tutoringSessionResponseConverter.fromEntity(t));


        return tutoringOfferResponse;
    }
}
