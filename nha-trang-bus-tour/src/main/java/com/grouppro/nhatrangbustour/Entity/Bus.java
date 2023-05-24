package com.grouppro.nhatrangbustour.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Bus")
@Table(name = "tblBus")
public class Bus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BusID", updatable = false)
    private Long BusID;
    @Column(name = "BusNumber", nullable = false)
    private String BusNumber;
    @Column(name = "Seat", nullable = false)
    private int Seat;
}
