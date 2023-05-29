package com.grouppro.nhatrangbustour.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.grouppro.nhatrangbustour.Entity.Payment;
import com.grouppro.nhatrangbustour.Entity.Ticket;
import com.grouppro.nhatrangbustour.Entity.User;
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
public class OrderDTO implements Serializable {
    private Long OrderID;
    private LocalDate OrderDate ;
    private Long UserID;
    private Long PaymentID;
    private List<Long> TicketListID;
}
