package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Bus")
@Table(name = "Bus")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BusID", updatable = false)
    private Long busId;
    @Column(name = "BusNumber", nullable = false)
    @JsonProperty("busNumber")
    private String busNumber;
    @Column(name = "Seat", nullable = false)
    @JsonProperty("seat")
    private int seat;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Trip> Trip;
}
