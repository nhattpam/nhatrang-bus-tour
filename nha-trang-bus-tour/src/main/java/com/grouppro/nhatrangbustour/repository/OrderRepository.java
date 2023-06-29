package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.Order;
import com.grouppro.nhatrangbustour.Entity.Payment;
import com.grouppro.nhatrangbustour.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByPayment(Payment payment);
    List<Order> findAllByUser(User user);
}
