package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.Plan;
import com.mitutor.repositories.IPlanRepository;
import com.mitutor.services.IPlanService;

@Service
public class PlanServiceImpl implements IPlanService {

	@Autowired
	private IPlanRepository planRepository;

	@Override
	public Optional<Plan> findById(Integer id) throws Exception {
		return planRepository.findById(id);
	}

	@Override
	public List<Plan> findAll() throws Exception {
		return planRepository.findAll();
	}

	@Override
	public Plan save(Plan t) throws Exception {
		return planRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		planRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		planRepository.deleteAll();
	}

}
