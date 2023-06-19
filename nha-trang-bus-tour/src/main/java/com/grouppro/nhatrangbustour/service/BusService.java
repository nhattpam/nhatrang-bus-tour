package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.Bus;
import com.grouppro.nhatrangbustour.repository.BusRepostory;
import com.grouppro.nhatrangbustour.service.interfaces.IBusService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BusService implements IBusService {
    private final BusRepostory busRepostory;

    @Override
    public List<Bus> getBuses() {
        return busRepostory.findAll();
    }

    @Override
    public Long save(Bus bus) {
        busRepostory.save(bus);
        if(bus!=null){
            return bus.getBusId();
        }
        return null;
    }

    @Override
    public Bus getBusById(Long busId) {
        return busRepostory.findById(busId).orElse(null);
    }
    
    @Override
    public void deleteBusById(Long id) {
    Optional<Bus> busOptional = busRepostory.findById(id);
    
    if (busOptional.isPresent()) {
        Bus bus = busOptional.get();
        busRepostory.delete(bus);
    } else {
        throw new IllegalArgumentException("Bus not found with id: " + id);
    }
}

    
}
