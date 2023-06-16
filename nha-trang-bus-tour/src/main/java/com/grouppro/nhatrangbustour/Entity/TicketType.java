package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class TicketType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TicketTypeID", updatable = false)
    private Long ticketTypeId;
    @Column(name = "TicketTypeName", nullable = false)
    private String ticketTypeName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "RouteID")
    private Route route;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ticket> Ticket;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PriceFrameTicket> PriceFrameTicket;
}
