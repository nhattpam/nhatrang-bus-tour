package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepostory extends JpaRepository<Driver, Long> {
}
