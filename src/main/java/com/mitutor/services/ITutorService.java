package com.mitutor.services;

import com.mitutor.entities.Tutor;

import java.util.List;

public interface ITutorService extends ICrudService<Tutor> {
    List<Tutor> findAllByUniversityIdAndCourseId(Integer universityId, Integer courseId);
}
