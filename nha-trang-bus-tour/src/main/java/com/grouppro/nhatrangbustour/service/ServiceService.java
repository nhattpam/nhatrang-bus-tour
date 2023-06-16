package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.ServiceRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IServiceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceService implements IServiceService {
    private final ServiceRepository serviceRepository;

    @Override
    public List<com.grouppro.nhatrangbustour.Entity.Service> getServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Long saveService(com.grouppro.nhatrangbustour.Entity.Service service) {
        serviceRepository.save(service);
        if(service!=null){
            return service.getServiceId();
        }
        return null;
    }

    @Override
    public com.grouppro.nhatrangbustour.Entity.Service getServiceById(Long sid) {
        return serviceRepository.getReferenceById(sid);
    }
}
