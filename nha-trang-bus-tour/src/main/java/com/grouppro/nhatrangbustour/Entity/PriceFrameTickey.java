package com.grouppro.nhatrangbustour.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceFrameTickey implements Serializable {
    private Long PriceFrameTicketID;
    private Double Price;
    private Long PriceFrameID;
    private Long TicketTypeID;
}
