package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.TutoringSessionsStudent;

@Repository
public interface ITutoringSessionsStudentRepository extends JpaRepository<TutoringSessionsStudent, Integer> {
	
}
