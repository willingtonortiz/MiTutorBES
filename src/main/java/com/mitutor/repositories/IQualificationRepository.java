package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.Qualification;

@Repository
public interface IQualificationRepository extends JpaRepository<Qualification, Integer> {

}
