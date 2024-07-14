package com.projectManagement.service;

import com.projectManagement.modal.PlanType;
import com.projectManagement.modal.Subscription;
import com.projectManagement.modal.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);
    Subscription getUsersSubscription(Long userId) throws Exception;
    Subscription upgradeSubscription(Long userId, PlanType planType) throws Exception;

    boolean isValid(Subscription subscription);
}


