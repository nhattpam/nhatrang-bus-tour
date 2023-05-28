package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Trip")
@Table(name = "tblTrip")
public class Trip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TripID", updatable = false)
    private Long TripID;
    @Column(name = "DepartureTime", nullable = false)
    private LocalDate DepartureTime;
    @Column(name = "ArrivalTime", nullable = false)
    private LocalDate ArrivalTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "RouteID")
    private Route Route;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "BusID")
    private Bus Bus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "DriverID")
    private Driver Driver;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "PriceFrameID")
    private PriceFrame PriceFrame;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Ticket> TicketList;
}
