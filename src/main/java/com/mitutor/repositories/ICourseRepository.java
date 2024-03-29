package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByUniversityIdAndName(Integer universityId, String name);

    List<Course> findAllByUniversityId(Integer universityId);

    List<Course> findAllByTutorCoursesTutorId(Integer tutorId);


}
