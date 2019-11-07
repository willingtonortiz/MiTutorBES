package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.Person;
import com.mitutor.entities.Qualification;
import com.mitutor.entities.Tutor;

import java.util.*;

@Repository
public interface IQualificationRepository extends JpaRepository<Qualification, Integer> {
	
	@Query("SELECT q from Qualification q WHERE q.adressee = :adressee_id")
	List<Qualification> findAllCommentsByTutor(@Param("adressee_id") Person adressee_id);
}
