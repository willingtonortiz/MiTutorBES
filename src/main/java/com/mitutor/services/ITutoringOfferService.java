package com.mitutor.services;

import com.mitutor.entities.TutoringOffer;

import java.util.List;

public interface ITutoringOfferService extends ICrudService<TutoringOffer> {
    List<TutoringOffer> findAllByUniversityIdAndCourseId(Integer universityId, Integer courseId);
}
