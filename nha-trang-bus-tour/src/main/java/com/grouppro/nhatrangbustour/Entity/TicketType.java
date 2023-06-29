package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TicketType")
@Table(name = "TicketType")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class TicketType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TicketTypeID", updatable = false)
    private Long ticketTypeId;
    @Column(name = "TicketTypeName", nullable = false)
    private String ticketTypeName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RouteID")
    @JsonBackReference
    private Route route;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Ticket> Ticket;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PriceFrameTicket> PriceFrameTicket;
}
