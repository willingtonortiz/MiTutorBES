package com.mitutor.services;

import com.mitutor.dtos.input.CreateTutorInput;
import com.mitutor.entities.Tutor;
import com.mitutor.entities.University;

import java.util.List;
import java.util.Optional;

public interface ITutorService extends ICrudService<Tutor> {
    List<Tutor> findAllByUniversityIdAndCourseId(Integer universityId, Integer courseId);
    Tutor subscription(CreateTutorInput createTutorInput) throws Exception;
    Optional<University> findUniversityByTutorId(Integer tutorId) throws Exception;

}
