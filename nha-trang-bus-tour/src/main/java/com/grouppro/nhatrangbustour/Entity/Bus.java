package com.grouppro.nhatrangbustour.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus implements Serializable {
    private Long BusID;
    private String BusName;
    private int Seat;
}
