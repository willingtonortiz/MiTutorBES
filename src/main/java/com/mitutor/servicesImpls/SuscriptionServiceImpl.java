package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.Suscription;
import com.mitutor.repositories.ISuscriptionRepository;
import com.mitutor.services.ISuscriptionService;

@Service
public class SuscriptionServiceImpl implements ISuscriptionService {

	@Autowired
	private ISuscriptionRepository suscriptionRepository;

	@Override
	public Optional<Suscription> findById(Integer id) throws Exception {
		return suscriptionRepository.findById(id);
	}

	@Override
	public List<Suscription> findAll() throws Exception {
		return suscriptionRepository.findAll();
	}

	@Override
	public Suscription save(Suscription t) throws Exception {
		return suscriptionRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		suscriptionRepository.deleteById(id);
	}

}
