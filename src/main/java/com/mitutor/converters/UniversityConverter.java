package com.mitutor.converters;

import org.springframework.stereotype.Component;

import com.mitutor.dtos.UniversityDto;
import com.mitutor.entities.University;


@Component
public class UniversityConverter implements IConverter<University, UniversityDto> {

	@Override
	public University fromDto(UniversityDto entity) {
		University university = new University();
		university.setId(entity.getId());
		university.setName(entity.getName());

		return university;
	}

	@Override
	public UniversityDto fromEntity(University dto) {
		UniversityDto universityDto = new UniversityDto();
		universityDto.setId(dto.getId());
		universityDto.setName(dto.getName());

		return universityDto;
	}

}
