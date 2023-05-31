package com.grouppro.nhatrangbustour.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.StationRoute;
import com.grouppro.nhatrangbustour.Entity.TicketType;
import com.grouppro.nhatrangbustour.Entity.Trip;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO implements Serializable {
    private Long RouteID;
    private String RouteName;
    private Long ParentRouteID;
    private List<Long> stationRouteListID;
    private List<Long> TickeTypeListID;
    private List<Long> TripListID;
    private List<Long> PriceFrameListID;
}
