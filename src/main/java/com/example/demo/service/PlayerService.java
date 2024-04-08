package com.example.demo.service;

import com.example.demo.model.Simulation;
import com.example.demo.model.Subscription;
import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.SimulationRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {


    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    SimulationService simulationService;

    public void populate() {

        // locale in english
        Faker faker = new Faker(new Locale("en-GB"));

        List<Simulation> simulations;
        //Date date = new Date();

        // ref variable creation UUID
        String uniqueID;

        for (int i = 0; i <10 ; i++ ){

            uniqueID = UUID.randomUUID().toString();
            Player player =  new Player();
            player.setId(uniqueID);
            player.setActive(true);
            player.setPlayer( faker.artist().name());
            player.setAge(faker.number().numberBetween(10, 100));

            simulations = simulationService.createFakeSimulations();

            for (int j = 0; j <10 ; j++ ) {
                player.addSimulation(simulations.get(j));
            }
            playerRepository.save(player);

        }
    }

    @Autowired
    PlayerRepository playerRepositorySubscription;
    @Autowired
    SubscriptionService subscriptionService;

    public void populateSubscriptionDB() {

        // locale in english
        Faker faker = new Faker(new Locale("en-GB"));

        List<Subscription> subscriptions;
        //Date date = new Date();

        // ref variable creation UUID
        String uniqueID;

        for (int i = 0; i <10 ; i++ ){

            uniqueID = UUID.randomUUID().toString();
            Player player =  new Player();
            player.setId(uniqueID);
            player.setActive(true);
            player.setPlayer( faker.artist().name());
            player.setAge(faker.number().numberBetween(10, 100));

            subscriptions = subscriptionService.createFakeSubscriptions();

            for (int j = 0; j <10 ; j++ ) {
                subscriptions.add(subscriptions.get(j));
            }
            playerRepository.save(player);

        }
    }
}

