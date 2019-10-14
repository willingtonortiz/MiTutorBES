package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.TutoringSession;
import com.mitutor.repositories.ITutoringSessionRepository;
import com.mitutor.services.ITutoringSessionService;

@Service
public class TutoringSessionImpl implements ITutoringSessionService {

	@Autowired
	private ITutoringSessionRepository tutoringSessionRepository;

	@Override
	public Optional<TutoringSession> findById(Integer id) throws Exception {
		return tutoringSessionRepository.findById(id);
	}

	@Override
	public List<TutoringSession> findAll() throws Exception {
		return tutoringSessionRepository.findAll();
	}

	@Override
	public TutoringSession save(TutoringSession t) throws Exception {
		return tutoringSessionRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		tutoringSessionRepository.deleteById(id);
	}

}
