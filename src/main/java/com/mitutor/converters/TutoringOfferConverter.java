package com.mitutor.converters;

import com.mitutor.dtos.TutoringOfferDTO;
import com.mitutor.entities.TutoringOffer;
import org.springframework.stereotype.Component;

@Component
public class TutoringOfferConverter implements IConverter<TutoringOffer, TutoringOfferDTO> {
    @Override
    public TutoringOffer fromDto(TutoringOfferDTO dto) {
        return new TutoringOffer()
                .withId(dto.getId())
                .withStartTime(dto.getStartTime())
                .withEndTime(dto.getEndTime())
                .withCapacity(dto.getCapacity())
                .withDescription(dto.getDescription());
    }

    @Override
    public TutoringOfferDTO fromEntity(TutoringOffer entity) {
        return new TutoringOfferDTO()
                .withId(entity.getId())
                .withStartTime(entity.getStartTime())
                .withEndTime(entity.getEndTime())
                .withCapacity(entity.getCapacity())
                .withDescription(entity.getDescription());
    }
}
