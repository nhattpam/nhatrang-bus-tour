package com.grouppro.nhatrangbustour.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.grouppro.nhatrangbustour.Entity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO implements Serializable {
    private Long TripID;
    private LocalDate DepartureTime;
    private LocalDate ArrivalTime;
    private Long RouteID;
    private Long BusID;
    private Long DriverID;
    private Long PriceFrameID;
    private List<Long> TicketListID;
}
