package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Ticket")
@Table(name = "Ticket")
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TicketID", updatable = false)
    private Long ticketId;
    @Nationalized
    @Column(
            name = "PassengerName",
            nullable = false,
            length = 100
    )
    private String passengerName;
    @Nationalized
    @Column(
            name = "PassengerPhone",
            nullable = false,
            length = 100
    )
    private String passengerPhone;
    @Nationalized
    @Column(
            name = "PassengerEmail",
            nullable = false,
            length = 100
    )
    private String passengerEmail;
    @Nationalized
    @Column(
            name = "Feedback",
            nullable = false,
            length = 100
    )
    private String feedback;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TripID")
    @JsonBackReference
    private Trip trip;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID")
    @JsonBackReference
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ServiceID")
    @JsonBackReference
    private Service service;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TicketTypeID")
    @JsonBackReference
    private TicketType ticketType;
}
