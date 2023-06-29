package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Service;

import java.util.List;

public interface IServiceService {
    List<Service> getServices();
    Long saveService(Service service);
    Service getServiceById(Long sid);
}
