package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.Entity.Station;
import com.grouppro.nhatrangbustour.Entity.StationRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRouteRepository extends JpaRepository<StationRoute, Long> {
    List<StationRoute> findAllByRoute(Route route);
    List<StationRoute> findAllByStation(Station station);

}
