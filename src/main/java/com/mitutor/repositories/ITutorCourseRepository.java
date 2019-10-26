package com.mitutor.repositories;

import com.mitutor.entities.TutorCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITutorCourseRepository extends JpaRepository<TutorCourse, Integer> {

}
