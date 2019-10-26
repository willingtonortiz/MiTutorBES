package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.Tutor;

import java.util.List;

@Repository
public interface ITutorRepository extends JpaRepository<Tutor, Integer> {


    @Query(value = "SELECT t FROM Tutor t WHERE t.person.university.id = :universityId AND :courseId = ANY(SELECT tc.course.id FROM TutorCourse tc WHERE tc.tutor.id = t.id) ")
    List<Tutor> findAllByUniversityIdAndCourseId(
            @Param("universityId") int universityId,
            @Param("courseId") int courseId
    );
}
