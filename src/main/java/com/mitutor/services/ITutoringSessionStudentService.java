package com.mitutor.services;

import java.util.List;

import com.mitutor.entities.TutoringSession;
import com.mitutor.entities.TutoringSessionsStudent;
import com.mitutor.entities.User;

public interface ITutoringSessionStudentService extends ICrudService<TutoringSessionsStudent>{
	List<TutoringSession> findSessionsByUserId(Integer id);

}
