package com.mitutor.converters;

import com.mitutor.dtos.Requests.TutoringSessionsRequests;
import com.mitutor.entities.Topic;
import com.mitutor.entities.TutoringOffer;
import com.mitutor.entities.TutoringSession;
import com.mitutor.services.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TutoringSessionRequestConvert implements IConverter<TutoringSession, TutoringSessionsRequests> {

    @Autowired
    ITopicService topicService;

    @Override
    public TutoringSession fromDto(TutoringSessionsRequests dto) {
        TutoringSession tutoringSession = new TutoringSession();

        tutoringSession.setDescription(dto.getDescription());
        tutoringSession.setPlace(dto.getPlace());
        tutoringSession.setPrice(dto.getPrice());
        tutoringSession.setStartTime(dto.getStartTime());
        tutoringSession.setEndTime(dto.getEndTime());

        try {
            for (int t: dto.getTopics())
                tutoringSession.getTopics().add(topicService.findById(t).get());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tutoringSession;
    }

    @Override
    public TutoringSessionsRequests fromEntity(TutoringSession entity) {
        return null;
    }
}
