package com.mitutor.converters;

// entity, dto
public interface IConverter<T, U> {
	T fromDto(U entity);
	
	U fromEntity(T dto);
}
