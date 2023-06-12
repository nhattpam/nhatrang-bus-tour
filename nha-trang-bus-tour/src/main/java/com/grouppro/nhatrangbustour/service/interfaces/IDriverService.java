package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Driver;

import java.util.List;

public interface IDriverService {
    List<Driver> getDrivers();
    List<Driver> getDriverById(Long id);
    Long saveDriver(String driverName, String driverphone);
}
