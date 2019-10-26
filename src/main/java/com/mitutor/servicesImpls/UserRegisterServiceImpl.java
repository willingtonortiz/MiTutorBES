package com.mitutor.servicesImpls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.Person;
import com.mitutor.entities.Student;
import com.mitutor.entities.User;
import com.mitutor.repositories.IPersonRepository;
import com.mitutor.repositories.IStudentRepository;
import com.mitutor.repositories.IUserRepository;
import com.mitutor.services.IUserRegisterService;

@Service
public class UserRegisterServiceImpl implements IUserRegisterService {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IPersonRepository personRepository;
	
	@Autowired
	private IStudentRepository studentRepository;
	
	@Override
	public User register(Person person, Student student, User user) throws Exception {
		// TODO Auto-generated method stub
		
		personRepository.save(person);
		
		User newuser = userRepository.save(user);
		
		studentRepository.save(student);
		
		return newuser;
	}

}
