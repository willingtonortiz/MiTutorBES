package com.mitutor.converters;

import com.mitutor.dtos.Requests.TutoringSessionsRequests;
import com.mitutor.entities.TutoringSession;
import org.springframework.stereotype.Component;

@Component
public class TutoringSessionRequestConvert implements IConverter<TutoringSession, TutoringSessionsRequests> {
    @Override
    public TutoringSession fromDto(TutoringSessionsRequests dto) {
        return null;
    }

    @Override
    public TutoringSessionsRequests fromEntity(TutoringSession entity) {
        return null;
    }
}
