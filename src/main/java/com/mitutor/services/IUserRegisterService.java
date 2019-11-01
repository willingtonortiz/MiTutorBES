package com.mitutor.services;



import java.util.Optional;

import com.mitutor.entities.Person;
import com.mitutor.entities.Student;
import com.mitutor.entities.User;

public interface IUserRegisterService {
	User register(Person person, Student student, User user) throws Exception;
	Optional<User> findyByUsername(String username) throws Exception;
	Optional<User> findByEmail(String email) throws Exception;
}
