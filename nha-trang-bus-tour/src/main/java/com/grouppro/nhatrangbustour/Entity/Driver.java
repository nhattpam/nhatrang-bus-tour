package com.grouppro.nhatrangbustour.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Driver")
@Table(name = "tblDriver")
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
}
