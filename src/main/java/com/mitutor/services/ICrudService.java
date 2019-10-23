package com.mitutor.services;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T> {
	public Optional<T> findById(Integer id) throws Exception;

	public List<T> findAll() throws Exception;

	public T save(T t) throws Exception;

	public void deleteById(Integer id) throws Exception;
	
	public void deleteAll() throws Exception;
}
