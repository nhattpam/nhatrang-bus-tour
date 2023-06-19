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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Order")
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID", updatable = false)
    private Long orderId;

    @Column(name = "OrderDate", nullable = false)
    private LocalDate orderDate ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PaymentID")
    @JsonBackReference
    private Payment payment;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Ticket> Ticket;

    // Add getters and setters for paymentId and userId

    public Long getPaymentId() {
        if (payment != null) {
            return payment.getPaymentId();
        }
        return null;
    }

    public Long getUserId() {
        if (user != null) {
            return user.getUserId();
        }
        return null;
    }
}
