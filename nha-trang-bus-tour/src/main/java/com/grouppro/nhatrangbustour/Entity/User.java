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
@Entity(name = "User")
@Table(name = "User")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID", updatable = false)
    private Long userId;
    @Nationalized
    @Column(
            name = "UserName",
            nullable = false,
            length = 100
    )
    private String userName;
    @Nationalized
    @Column(
            name = "UserEmail",
            nullable = false,
            length = 100
    )
    private String userEmail;
    @Nationalized
    @Column(
            name = "UserPhone",
            nullable = false,
            length = 100
    )
    private String userPhone;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Order> Order;

}
