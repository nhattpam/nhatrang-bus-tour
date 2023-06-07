package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.Driver;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepostory extends JpaRepository<Driver, Long> {
    List<Driver> findAll();
}
