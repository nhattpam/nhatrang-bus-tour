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
@Table(name = "PriceFrameTicket")
public class PriceFrameTicket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PriceFrameTicketID", updatable = false)
    private Long priceFrameTicketId;
    @Column(name = "Price", nullable = false)
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "PriceFrameID")
    private PriceFrame priceFrame;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "TicketTypeID")
    private TicketType TicketType;
}
