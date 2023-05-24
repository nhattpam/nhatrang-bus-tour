package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Service")
@Table(name = "tblService")
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceID", updatable = false)
    private Long ServiceID;
    @Nationalized
    @Column(
            name = "ServiceNumber",
            nullable = false,
            length = 100
    )
    private String ServiceNumber;
    @Nationalized
    @Column(
            name = "ServiceName",
            nullable = false,
            length = 100
    )
    private String ServiceName;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Ticket> TicketList;
}
