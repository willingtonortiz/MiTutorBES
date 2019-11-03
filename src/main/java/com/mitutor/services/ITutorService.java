package com.mitutor.services;

import com.mitutor.dtos.input.CreateTutorInput;
import com.mitutor.entities.Tutor;

import java.util.List;

public interface ITutorService extends ICrudService<Tutor> {
    List<Tutor> findAllByUniversityIdAndCourseId(Integer universityId, Integer courseId);
    Tutor subscription(CreateTutorInput createTutorInput) throws Exception;

}
