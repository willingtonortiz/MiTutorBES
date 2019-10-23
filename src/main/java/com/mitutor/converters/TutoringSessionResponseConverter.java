package com.mitutor.converters;

import com.mitutor.dtos.Responses.TutoringOfferResponse;
import com.mitutor.dtos.Responses.TutoringSessionResponse;
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
        return  null;
    }
}
