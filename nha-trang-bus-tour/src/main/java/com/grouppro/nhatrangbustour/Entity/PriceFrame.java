package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Entity(name = "PriceFrame")
@Table(name = "PriceFrame")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PriceFrame implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PriceFrameID", updatable = false)
    private Long priceFrameId;
    @Nationalized
    @Column(name = "PriceFrameName", nullable = false)
    private String priceFrameName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "RouteID")
    private Route route;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PriceFrameTicket> PriceFrameTicket;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Trip> Trip;
}
