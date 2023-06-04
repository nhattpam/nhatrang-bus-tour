package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.StationRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRouteRepository extends JpaRepository<StationRoute, Long> {
}
