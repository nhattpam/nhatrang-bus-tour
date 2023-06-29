package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Driver;

import java.util.List;

public interface IDriverService {
    List<Driver> getDrivers();
    Long saveDriver(Driver driver);
    Driver getDriverById(Long did);
}
