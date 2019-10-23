package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.Qualification;
import com.mitutor.repositories.IQualificationRepository;
import com.mitutor.services.IQualificationService;

@Service
public class QualificationServiceImpl implements IQualificationService {

	@Autowired
	private IQualificationRepository qualificationRepository;

	@Override
	public Optional<Qualification> findById(Integer id) throws Exception {
		return qualificationRepository.findById(id);
	}

	@Override
	public List<Qualification> findAll() throws Exception {
		return qualificationRepository.findAll();
	}

	@Override
	public Qualification save(Qualification t) throws Exception {
		return qualificationRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		qualificationRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		qualificationRepository.deleteAll();
	}

}
