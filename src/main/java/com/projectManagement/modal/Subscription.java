package com.projectManagement.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate subscriptionStartDate;
    private LocalDate getSubscriptionEndDate;
    private PlanType planType;
    private boolean isValid;
    @OneToOne
    private User user;
}
