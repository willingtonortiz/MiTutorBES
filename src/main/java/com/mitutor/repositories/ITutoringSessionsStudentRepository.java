package com.mitutor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.TutoringSession;
import com.mitutor.entities.TutoringSessionsStudent;
import com.mitutor.entities.User;

@Repository
public interface ITutoringSessionsStudentRepository extends JpaRepository<TutoringSessionsStudent, Integer> {
	
	
}
