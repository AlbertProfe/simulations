package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Subscription;
import com.example.demo.repository.SubscriptionRepository;
import com.github.javafaker.Faker;

@Service

public class SubscriptionService {
    //static ArrayList<Subscription> subscriptions = new ArrayList<>();

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public List<Subscription> createFakeSubscriptions() {

        // locale in english
        Faker faker = new Faker(new Locale("en-GB"));
        Date date = new Date();
        List<Subscription> subscriptions = new ArrayList<>();

        // ref variable creation UUID
        String uniqueID;

        for (int i = 0; i <10 ; i++ ){

            uniqueID = UUID.randomUUID().toString();
            Subscription subscription = new Subscription( uniqueID,
                    date.toString(),
                    faker.number().numberBetween(10, 100),
                    faker.bool().bool(), null 
            );
            subscriptions.add(subscription);


        }

        return subscriptions;
    }

    public List<Subscription> populate() {

        List<Subscription> subscriptions = createFakeSubscriptions();

        for (int i = 0; i <10 ; i++ ){
            subscriptionRepository.save(subscriptions.get(i));
            subscriptions.add(subscriptions.get(i));
        }

        return subscriptions;
    }
}
