package com.mitutor.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT u from User u WHERE u.username = :username")
	Optional<User> findByUsername(@Param("username") String username) throws Exception;

	@Query(value = "SELECT u from User u WHERE u.email = :email")
	Optional<User> findByEmail(@Param("email") String email) throws Exception;

	
}
