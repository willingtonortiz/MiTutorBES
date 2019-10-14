package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitutor.entities.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Integer>{

}
