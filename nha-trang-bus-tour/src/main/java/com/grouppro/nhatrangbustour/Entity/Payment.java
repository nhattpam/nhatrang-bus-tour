package com.grouppro.nhatrangbustour.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Payment")
@Table(name = "tblPayment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID", updatable = false)
    private Long PaymentID;
    @Column(name = "PaymentDate", nullable = false)
    private LocalDate PaymentDate;
    @Column(name = "PaymentMethod", nullable = false)
    private String PaymentMethod;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Order> OrderList;
}
