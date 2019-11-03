package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.TutoringOffer;

import java.util.List;

@Repository
public interface ITutoringOfferRepository extends JpaRepository<TutoringOffer, Integer> {
    List<TutoringOffer> findAllByUniversityIdAndCourseId(Integer universityId, Integer courseId);

    List<TutoringOffer> findAllByTutorId(Integer tutorId);
}
