package com.mitutor.converters;

import com.mitutor.dtos.Responses.TutoringSessionResponse;
import com.mitutor.entities.Topic;
import com.mitutor.entities.TutoringSession;
import org.springframework.stereotype.Component;

@Component
public class TutoringSessionResponseConverter implements IConverter<TutoringSession, TutoringSessionResponse> {
    @Override
    public TutoringSession fromDto(TutoringSessionResponse dto) {
        return null;
    }

    @Override
    public TutoringSessionResponse fromEntity(TutoringSession entity) {

        TutoringSessionResponse tutoringSessionResponse = new TutoringSessionResponse();
        tutoringSessionResponse.setDescription(entity.getDescription());
        tutoringSessionResponse.setEndTime(entity.getEndTime());
        tutoringSessionResponse.setId(entity.getId());
        tutoringSessionResponse.setPlace(entity.getPlace());
        tutoringSessionResponse.setPrice(entity.getPrice());
        tutoringSessionResponse.setStartTime(entity.getStartTime());
        tutoringSessionResponse.setStudentCount(entity.getStudentCount());

        for (Topic t : entity.getTopics())
            tutoringSessionResponse.getTopicsName().add(t.getName());


        return tutoringSessionResponse;
    }
}
