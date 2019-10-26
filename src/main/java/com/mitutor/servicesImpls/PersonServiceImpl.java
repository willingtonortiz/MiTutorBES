package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.Person;
import com.mitutor.repositories.IPersonRepository;
import com.mitutor.services.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private IPersonRepository personRepository;

	@Override
	public Optional<Person> findById(Integer id) throws Exception {
		return personRepository.findById(id);
	}

	@Override
	public List<Person> findAll() throws Exception {
		return personRepository.findAll();
	}

	@Override
	public Person save(Person t) throws Exception {
		return personRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		personRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		personRepository.deleteAll();
	}

}
