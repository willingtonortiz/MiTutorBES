package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.Topic;

import java.util.List;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Integer> {
    List<Topic> findAllByCourseId(Integer courseId);
}
