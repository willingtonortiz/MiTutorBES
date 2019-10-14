package com.mitutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitutor.entities.TutoringOffer;

@Repository
public interface ITutoringOfferRepository extends JpaRepository<TutoringOffer, Integer> {

}
