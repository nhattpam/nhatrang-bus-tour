package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Driver implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DriverID", updatable = false)
    private Long DriverID;
    @Column(name = "DriverPhone", nullable = false, length = 20)
    private String DriverPhone;
    @Nationalized
    @Column(name = "DriverName",nullable = false)
    private String DriverName;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Trip> Trip;
}
