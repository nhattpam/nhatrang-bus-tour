package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
