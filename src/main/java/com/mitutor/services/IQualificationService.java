package com.mitutor.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.mitutor.entities.Person;
import com.mitutor.entities.Qualification;
import com.mitutor.entities.Tutor;

public interface IQualificationService extends ICrudService<Qualification> {
	List<Qualification> findAllCommentsByTutor(Person tutorId);
}
