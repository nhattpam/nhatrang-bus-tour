package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceFrameRepository extends JpaRepository<PriceFrame, Long> {
}
