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
@Entity(name = "PriceFrame")
@Table(name = "tblPriceFrame")
public class PriceFrame implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PriceFrameID", updatable = false)
    private Long PriceFrameID;
    @Nationalized
    @Column(name = "PriceFrameName", nullable = false)
    private String PriceFrameName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "RouteID")
    private Route RouteID;
}