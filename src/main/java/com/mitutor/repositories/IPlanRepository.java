package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.Plan;

@Repository
public interface IPlanRepository extends JpaRepository<Plan, Integer> {

}
