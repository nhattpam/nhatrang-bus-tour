package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.Entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    List<TicketType> findAllByRoute(Route route);
}
