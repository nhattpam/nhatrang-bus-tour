package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Bus;

import java.util.List;

public interface IBusService {
    List<Bus> getBuses();
    Long save(String BusNumber, int seat);
}
