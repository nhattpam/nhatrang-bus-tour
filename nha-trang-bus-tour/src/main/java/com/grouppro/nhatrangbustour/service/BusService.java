package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.Bus;
import com.grouppro.nhatrangbustour.repository.BusRepostory;
import com.grouppro.nhatrangbustour.service.interfaces.IBusService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Long save(String BusNumber, int seat) {
        Bus bus = new Bus();
        bus.setBusNumber(BusNumber);
        bus.setSeat(seat);
        busRepostory.save(bus);
        if(bus!=null){
            return bus.getBusID();
        }
        return null;
    }
}
