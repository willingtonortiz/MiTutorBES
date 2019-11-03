package com.mitutor.converters;

import org.springframework.stereotype.Component;

import com.mitutor.dtos.UniversityDTO;
import com.mitutor.entities.University;


@Component
public class UniversityConverter implements IConverter<University, UniversityDTO> {

	@Override
	public University fromDto(UniversityDTO entity) {
		University university = new University();
		university.setId(entity.getId());
		university.setName(entity.getName());

		return university;
	}

	@Override
	public UniversityDTO fromEntity(University dto) {
		UniversityDTO universityDto = new UniversityDTO();
		universityDto.setId(dto.getId());
		universityDto.setName(dto.getName());

		return universityDto;
	}

}
