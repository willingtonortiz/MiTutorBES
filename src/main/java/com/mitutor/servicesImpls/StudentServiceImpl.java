package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.Student;
import com.mitutor.repositories.IStudentRepository;
import com.mitutor.services.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IStudentRepository studentRepository;

	@Override
	public Optional<Student> findById(Integer id) throws Exception {
		return studentRepository.findById(id);
	}

	@Override
	public List<Student> findAll() throws Exception {
		return studentRepository.findAll();
	}

	@Override
	public Student save(Student t) throws Exception {
		return studentRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		studentRepository.deleteById(id);
	}

}
