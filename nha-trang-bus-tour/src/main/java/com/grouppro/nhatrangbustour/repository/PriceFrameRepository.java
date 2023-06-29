package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceFrameRepository extends JpaRepository<PriceFrame, Long> {
    List<PriceFrame> findAllByRoute(Route route);
}
