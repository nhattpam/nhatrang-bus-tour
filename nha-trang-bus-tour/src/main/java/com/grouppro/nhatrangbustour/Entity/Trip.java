package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.*;
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
@Table(name = "Trip")
public class Trip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TripID", updatable = false)
    private Long tripID;
    @Column(name = "DepartureTime", nullable = false)
    private LocalDate departureTime;
    @Column(name = "ArrivalTime", nullable = false)
    private LocalDate arrivalTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "RouteID")
    private Route route;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BusID")
    @JsonBackReference
    private Bus bus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DriverID")
    @JsonBackReference
    private Driver driver;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "PriceFrameID")
    private PriceFrame priceFrame;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Ticket> Ticket;
}
