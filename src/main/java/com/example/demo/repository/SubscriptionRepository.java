package com.example.demo.repository;

import com.example.demo.model.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, String> {}
