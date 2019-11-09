package com.mitutor.services;

import java.util.List;

import com.mitutor.entities.Person;
import com.mitutor.entities.Qualification;

public interface IQualificationService extends ICrudService<Qualification> {
	List<Qualification> findAllCommentsByTutor(Person tutorId);
}
