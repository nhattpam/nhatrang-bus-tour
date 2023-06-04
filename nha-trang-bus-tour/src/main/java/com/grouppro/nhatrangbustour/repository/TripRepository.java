package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}
