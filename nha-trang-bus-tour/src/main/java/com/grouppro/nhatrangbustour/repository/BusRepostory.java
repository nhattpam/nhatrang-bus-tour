package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepostory extends JpaRepository<Bus, Long> {

}
