package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "StationRoute")
@Table(name = "StationRoute")
public class StationRoute implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "StationRouteID",
            updatable = false
    )
    private Long StationRouteID;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "StationID")
    private Station Station;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "RouteID")
    private Route Route;
}
