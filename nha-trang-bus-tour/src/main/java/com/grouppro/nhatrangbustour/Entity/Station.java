package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Entity(name = "Station")
@Table(name = "Station")
public class Station implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StationID",
            updatable = false
    )
    private Long stationId;
    @Nationalized
    @Column(
            name = "StationName",
            nullable = false,
            length = 100
    )
    private String stationName;
    @Nationalized
    @Column(
            name = "StationLocation",
            nullable = false,
            length = 100
    )
    private String stationLocation;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<StationRoute> stationRoute;

}
