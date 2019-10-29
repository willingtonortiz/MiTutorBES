package com.mitutor.services;

import java.util.Optional;

import com.mitutor.dtos.input.CreateTutorInput;
import com.mitutor.entities.Tutor;
import com.mitutor.entities.User;

public interface IUserService extends ICrudService<User> {
	
	Tutor subscription(CreateTutorInput createTutorInput) throws Exception;
}
