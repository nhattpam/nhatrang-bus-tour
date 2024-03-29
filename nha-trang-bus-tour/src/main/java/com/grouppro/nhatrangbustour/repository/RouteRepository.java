package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.Entity.StationRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByRouteNameContaining (String routename);
    List<Route> findByParentRouteID(Long parentid);
}
