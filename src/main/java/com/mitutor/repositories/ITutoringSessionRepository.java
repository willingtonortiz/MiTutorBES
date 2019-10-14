package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.TutoringSession;

@Repository
public interface ITutoringSessionRepository extends JpaRepository<TutoringSession, Integer> {

}
