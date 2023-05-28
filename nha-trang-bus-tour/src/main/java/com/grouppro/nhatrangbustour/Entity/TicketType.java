package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "tblTicketType")
public class TicketType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TicketTypeID", updatable = false)
    private Long TicketTypeID;
    @Column(name = "TicketTypeName", nullable = false)
    private String TicketTypeName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "RouteID")
    private Route Route;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Ticket> TicketList;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PriceFrameTicket> PriceFrameTickeList;
}
