package com.mitutor.converters;

import com.mitutor.dtos.TopicDTO;
import com.mitutor.entities.Topic;
import org.springframework.stereotype.Component;

@Component
public class TopicConverter implements IConverter<Topic, TopicDTO> {
    @Override
    public Topic fromDto(TopicDTO dto) {
        return new Topic()
                .withId(dto.getId())
                .withName(dto.getName());
    }

    @Override
    public TopicDTO fromEntity(Topic entity) {
        return new TopicDTO()
                .withId(entity.getId())
                .withName(entity.getName());
    }
}
