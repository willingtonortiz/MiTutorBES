package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.AvailabilityDay;
import com.mitutor.repositories.IAvailabilityDayRepository;
import com.mitutor.services.IAvailabilityDayService;

@Service
public class AvailabilityDayServiceImpl implements IAvailabilityDayService {

	@Autowired
	private IAvailabilityDayRepository availabilityRepository;
	
	@Override
	public Optional<AvailabilityDay> findById(Integer id) throws Exception {
		return availabilityRepository.findById(id);
	}

	@Override
	public List<AvailabilityDay> findAll() throws Exception {
		return availabilityRepository.findAll();
	}

	@Override
	public AvailabilityDay save(AvailabilityDay t) throws Exception {
		return availabilityRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		availabilityRepository.deleteById(id);
	}

}
