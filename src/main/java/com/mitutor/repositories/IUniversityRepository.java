package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.University;

@Repository
public interface IUniversityRepository extends JpaRepository<University, Integer> {

}
