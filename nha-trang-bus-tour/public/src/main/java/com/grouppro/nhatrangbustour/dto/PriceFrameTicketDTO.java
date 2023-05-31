package com.grouppro.nhatrangbustour.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.TicketType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceFrameTicketDTO implements Serializable {
    private Long PriceFrameTicketID;
    private Double Price;
    private Long PriceFrameID;
    private Long TicketTypeID;
}
