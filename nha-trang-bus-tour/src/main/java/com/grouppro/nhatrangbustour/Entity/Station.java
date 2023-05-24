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
@Entity(name = "Station")
@Table(name = "tblStation")
public class Station implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StationID",
            updatable = false
    )
    private Long StationID;
    @Nationalized
    @Column(
            name = "StationName",
            nullable = false,
            length = 100
    )
    private String StationName;
    @Nationalized
    @Column(
            name = "StationLocation",
            nullable = false,
            length = 100
    )
    private String StationLocation;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<StationRoute> stationRouteList;

}
