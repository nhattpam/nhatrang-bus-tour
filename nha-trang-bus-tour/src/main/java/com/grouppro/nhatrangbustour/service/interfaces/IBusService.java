package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Bus;
import com.grouppro.nhatrangbustour.dto.BusDTO;

import java.util.List;

public interface IBusService {
    List<Bus> getBuses();
    List<Bus> getBusById(Long id);
    Long save(String BusNumber, int seat);
}
