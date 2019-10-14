package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.TutoringOffer;
import com.mitutor.repositories.ITutoringOfferRepository;
import com.mitutor.services.ITutoringOfferService;

@Service
public class TutoringOfferServiceImpl implements ITutoringOfferService {

	@Autowired
	private ITutoringOfferRepository tutoringOfferRepository;

	@Override
	public Optional<TutoringOffer> findById(Integer id) throws Exception {
		return tutoringOfferRepository.findById(id);
	}

	@Override
	public List<TutoringOffer> findAll() throws Exception {
		return tutoringOfferRepository.findAll();
	}

	@Override
	public TutoringOffer save(TutoringOffer t) throws Exception {
		return tutoringOfferRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		tutoringOfferRepository.deleteById(id);
	}

}
