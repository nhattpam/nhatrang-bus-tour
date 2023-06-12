package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.Bus;
import com.grouppro.nhatrangbustour.dto.BusDTO;
import com.grouppro.nhatrangbustour.mapper.BusMapper;
import com.grouppro.nhatrangbustour.repository.BusRepostory;
import com.grouppro.nhatrangbustour.service.interfaces.IBusService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
    public List<Bus> getBusById(Long id) {
        return busRepostory.findAllById(Collections.singleton(id));
    }

    @Override
    public Long save(String BusNumber, int seat) {
        Bus bus = new Bus();
//        boolean check = busRepostory.existsByBusID(bus.getBusID());
        bus.setBusNumber(BusNumber);
        bus.setSeat(seat);
        busRepostory.save(bus);
        if(bus!=null){
            return bus.getBusId();
        }
        return  null;
    }
}
