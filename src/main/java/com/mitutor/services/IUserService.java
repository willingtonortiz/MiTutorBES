package com.mitutor.services;

import java.util.Optional;

import com.mitutor.dtos.input.CreateTutorInput;
import com.mitutor.entities.Tutor;
import com.mitutor.entities.User;

public interface IUserService extends ICrudService<User> {

    Optional<User> findByUsername(String username) throws Exception;

    Tutor subscription(CreateTutorInput createTutorInput) throws Exception;
}
