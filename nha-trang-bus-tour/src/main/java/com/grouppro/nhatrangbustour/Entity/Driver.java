package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Driver")
@Table(name = "Driver")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Driver implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DriverID", updatable = false)
    private Long driverId;
    @Column(name = "DriverPhone", nullable = false, length = 20)
    private String driverPhone;
    @Nationalized
    @Column(name = "DriverName",nullable = false)
    private String driverName;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Trip> Trip;
}
