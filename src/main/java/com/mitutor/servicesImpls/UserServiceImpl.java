package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.dtos.input.CreateTutorInput;
import com.mitutor.entities.Tutor;
import com.mitutor.entities.User;
import com.mitutor.repositories.ITutorRepository;
import com.mitutor.repositories.IUserRepository;
import com.mitutor.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;



    @Override
    public Optional<User> findById(Integer id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public User save(User t) throws Exception {
        return userRepository.save(t);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        userRepository.deleteAll();
    }

    @Override
    public Optional<User> findByUsername(String username) throws Exception {
        return userRepository.findByUsername(username);
    }



}
