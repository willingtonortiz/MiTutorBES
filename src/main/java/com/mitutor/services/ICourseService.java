package com.mitutor.services;

import com.mitutor.entities.Course;
import com.mitutor.entities.Topic;

import java.util.List;
import java.util.Optional;

public interface ICourseService extends ICrudService<Course> {
    Optional<Course> findByUniversityIdAndName(Integer universityId, String name);

    List<Course> findAllByUniversityId(Integer universityId);

    List<Course> findAllByTutorId(Integer tutorId);
}
