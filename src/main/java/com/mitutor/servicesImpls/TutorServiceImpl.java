package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.Tutor;
import com.mitutor.repositories.ITutorRepository;
import com.mitutor.services.ITutorService;

@Service
public class TutorServiceImpl implements ITutorService {

	@Autowired
	private ITutorRepository tutorRepository;

	@Override
	public Optional<Tutor> findById(Integer id) throws Exception {
		return tutorRepository.findById(id);
	}

	@Override
	public List<Tutor> findAll() throws Exception {
		return tutorRepository.findAll();
	}

	@Override
	public Tutor save(Tutor t) throws Exception {
		return tutorRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		tutorRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		tutorRepository.deleteAll();
	}

}
