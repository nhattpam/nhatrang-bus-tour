package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "tblTicket")
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TicketID", updatable = false)
    private Long TicketID;
    @Nationalized
    @Column(
            name = "PassengerName",
            nullable = false,
            length = 100
    )
    private String PassengerName;
    @Nationalized
    @Column(
            name = "PassengerPhone",
            nullable = false,
            length = 100
    )
    private String PassengerPhone;
    @Nationalized
    @Column(
            name = "PassengerEmail",
            nullable = false,
            length = 100
    )
    private String PassengerEmail;
    @Nationalized
    @Column(
            name = "Feedback",
            nullable = false,
            length = 100
    )
    private String Feedback;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "TripID")
    private Long TripID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "OrderID")
    private Long OrderID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "ServiceID")
    private Long ServiceID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "TicketTypeID")
    private Long TicketTypeID;
}