package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.TutoringSessionsStudent;
import com.mitutor.repositories.ITutoringSessionsStudentRepository;
import com.mitutor.services.ITutoringSessionStudentService;
@Service
public class TutoringSessionStudent implements ITutoringSessionStudentService{

	@Autowired
	public ITutoringSessionsStudentRepository tutoringSessionStudentRepository;
	
	@Override
	public Optional<TutoringSessionsStudent> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return tutoringSessionStudentRepository.findById(id);
	}

	@Override
	public List<TutoringSessionsStudent> findAll() throws Exception {
		// TODO Auto-generated method stub
		return tutoringSessionStudentRepository.findAll();
	}

	@Override
	public TutoringSessionsStudent save(TutoringSessionsStudent t) throws Exception {
		// TODO Auto-generated method stub
		return tutoringSessionStudentRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		tutoringSessionStudentRepository.deleteById(id);
		
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
		tutoringSessionStudentRepository.deleteAll();
		
	}

}
