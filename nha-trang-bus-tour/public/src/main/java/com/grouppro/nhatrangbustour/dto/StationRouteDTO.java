package com.grouppro.nhatrangbustour.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.Entity.Station;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

public class StationRouteDTO implements Serializable {
    private Long StationRouteID;
    private Long StationID;
    private Long RouteID;
}
