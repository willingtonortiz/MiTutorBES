package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.Tutor;

@Repository
public interface ITutorRepository extends JpaRepository<Tutor, Integer> {

}
