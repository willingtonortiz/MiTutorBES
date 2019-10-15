package com.mitutor.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mitutor.entities.User;
import com.mitutor.repositories.IUserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {

			Optional<User> optionalUser = userRepository.findByUsername(username);
			User user = optionalUser.get();
			if (!optionalUser.isPresent()) {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
			
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public User save(User user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(newUser);
	}

}
