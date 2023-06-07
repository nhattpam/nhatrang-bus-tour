package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.lang.model.element.Name;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Route")
@Table(name = "Route")
public class Route implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RouteID", updatable = false)
    private Long routeId;
    @Nationalized
    @Column(
            name = "RouteName",
            nullable = false,
            length = 100
    )
    private String routeName;
    @Column(name = "ParentRouteID", updatable = false)
    private Long parentRouteID;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<StationRoute> stationRoute;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<TicketType> TickeType;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Trip> Trip;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PriceFrame> PriceFrame;
}
