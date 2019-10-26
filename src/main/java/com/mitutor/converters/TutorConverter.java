package com.mitutor.converters;

import com.mitutor.dtos.TutorDTO;
import com.mitutor.entities.Tutor;
import org.springframework.stereotype.Component;

@Component
public class TutorConverter implements IConverter<Tutor, TutorDTO> {
    @Override
    public Tutor fromDto(TutorDTO dto) {
        Tutor tutor = new Tutor();
        tutor.setId(dto.getId());
        tutor.setPoints(dto.getPoints());
        tutor.setQualificationCount(dto.getQualificationCount());
        tutor.setDescription(dto.getDescription());
        tutor.setStatus(dto.getStatus());

        return tutor;
    }

    @Override
    public TutorDTO fromEntity(Tutor entity) {
        TutorDTO tutorDTO = new TutorDTO();
        tutorDTO.setId(entity.getId());
        tutorDTO.setPoints(entity.getPoints());
        tutorDTO.setQualificationCount(entity.getQualificationCount());
        tutorDTO.setDescription(entity.getDescription());
        tutorDTO.setStatus(entity.getStatus());

        return tutorDTO;
    }
}
