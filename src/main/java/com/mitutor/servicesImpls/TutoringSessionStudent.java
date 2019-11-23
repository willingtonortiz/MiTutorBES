package com.mitutor.servicesImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.TutoringSession;
import com.mitutor.entities.TutoringSessionsStudent;
import com.mitutor.entities.User;
import com.mitutor.repositories.ITutoringSessionRepository;
import com.mitutor.repositories.ITutoringSessionsStudentRepository;
import com.mitutor.services.ITutoringSessionStudentService;
@Service
public class TutoringSessionStudent implements ITutoringSessionStudentService{

	@Autowired
	public ITutoringSessionsStudentRepository tutoringSessionStudentRepository;
	
	@Autowired
	public ITutoringSessionRepository tutoringSessionRepository;
	
	@Override
	public Optional<TutoringSessionsStudent> findById(Integer id) throws Exception {
		return tutoringSessionStudentRepository.findById(id);
	}

	@Override
	public List<TutoringSessionsStudent> findAll() throws Exception {
		return tutoringSessionStudentRepository.findAll();
	}

	@Override
	public TutoringSessionsStudent save(TutoringSessionsStudent t) throws Exception {
		return tutoringSessionStudentRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		tutoringSessionStudentRepository.deleteById(id);
		
	}

	@Override
	public void deleteAll() throws Exception {
		tutoringSessionStudentRepository.deleteAll();
	}

	@Override
	public List<TutoringSession> findSessionsByUserId(Integer id) {
		// TODO Auto-generated method stub
		List<TutoringSession> listReponse = new ArrayList<TutoringSession>();
		
		try {
			for(int i =0; i< this.findAll().size(); ++i) {
				if(this.findAll().get(i).getStudent().getId() == id) {
					listReponse.add(tutoringSessionRepository.findById(this.findAll().get(i).getTutoringSession().getId()).get());
				}
			}
			return listReponse;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
}
