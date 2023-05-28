package com.grouppro.nhatrangbustour.Entity;

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
@Entity(name = "PriceFrameTicket")
@Table(name = "tblPriceFrameTicket")
public class PriceFrameTicket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PriceFrameTicketID", updatable = false)
    private Long PriceFrameTicketID;
    @Column(name = "Price", nullable = false)
    private Double Price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "PriceFrameID")
    private PriceFrame PriceFrame;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "TicketTypeID")
    private TicketType TicketType;
}
