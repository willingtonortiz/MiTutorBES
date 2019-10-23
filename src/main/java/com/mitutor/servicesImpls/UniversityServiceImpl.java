package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.University;
import com.mitutor.repositories.IUniversityRepository;
import com.mitutor.services.IUniversityService;

@Service
public class UniversityServiceImpl implements IUniversityService {

	@Autowired
	private IUniversityRepository universityRepository;

	@Override
	public Optional<University> findById(Integer id) throws Exception {
		return universityRepository.findById(id);
	}

	@Override
	public List<University> findAll() throws Exception {
		return universityRepository.findAll();
	}

	@Override
	public University save(University t) throws Exception {
		return universityRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		universityRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		universityRepository.deleteAll();
	}

}
