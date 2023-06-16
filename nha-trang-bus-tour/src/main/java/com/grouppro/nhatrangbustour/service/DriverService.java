package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.repository.DriverRepostory;
import com.grouppro.nhatrangbustour.service.interfaces.IDriverService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DriverService implements IDriverService {
    private final DriverRepostory driverRepostory;

    @Override
    public List<Driver> getDrivers() {
        return driverRepostory.findAll();
    }

    @Override
    public Long saveDriver(Driver driver) {
        driverRepostory.save(driver);
        if(driver!=null){
            return driver.getDriverId();
        }
        return null;
    }

    @Override
    public Driver getDriverById(Long did) {
        return driverRepostory.getReferenceById(did);
    }
}
