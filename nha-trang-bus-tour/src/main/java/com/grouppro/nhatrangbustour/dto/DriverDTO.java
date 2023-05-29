package com.grouppro.nhatrangbustour.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.grouppro.nhatrangbustour.Entity.Trip;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
public class DriverDTO implements Serializable {
    private Long DriverID;
    private String DriverPhone;
    private String DriverName;
    private List<Long> TripListid;
}