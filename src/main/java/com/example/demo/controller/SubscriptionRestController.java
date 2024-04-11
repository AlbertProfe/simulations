package com.example.demo.controller;

import com.example.demo.model.Subscription;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/subscription/")
public class SubscriptionRestController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    //CRUD: read
    @RequestMapping("/subscriptions")
    public Iterable<Subscription> getAllSubscriptions (){

        return subscriptionRepository.findAll();

    }

    //CRUD: delete
    @DeleteMapping("/delete")
    public String deleteSubscription(@RequestParam String id) {

        //System.out.println("id:" + id);
        Optional<Subscription> subscriptionFound = subscriptionRepository.findById(id);
        long countBefore = subscriptionRepository.count();
        //System.out.println("simulationFound:" + simulationFound);

        if (subscriptionFound.isPresent()){
            subscriptionRepository.deleteById(id);
            long countAfter = subscriptionRepository.count();
            String response = "subscription deleted: " + id + " count: " + countBefore + "/" + countAfter;
            return response;
        } else return "id: " + id +  " not found " + " count: " + countBefore;

    }

    //CRUD: create
    @PostMapping(path="create", consumes = "application/JSON")
    public Subscription createSubscription(@RequestBody Subscription subscription){
        //
        Subscription subscriptionCreated = subscriptionRepository.save(subscription);
        return subscriptionCreated;
    }
    
    //CRUD: update
    @PutMapping("/update/{id}")
    public Subscription updateSubscription (@PathVariable String id, @RequestBody Subscription subscription) {

        Optional<Subscription> subscriptionFound = subscriptionRepository.findById(id);

        if (subscriptionFound.isPresent()) {
            Subscription subscriptionToUpdate = subscriptionFound.get();
            //
            subscriptionToUpdate.setPayment(subscription.getPayment());
            subscriptionToUpdate.setSubscriptionActive(subscription.getSubscriptionActive());

            Subscription subscriptionUpdated = subscriptionRepository.save(subscriptionToUpdate);
            return subscriptionUpdated;
        } else
            return null;

    }

    @RequestMapping("/populate")
    public String populateSubscriptionDB(){

        subscriptionService.populate();

        return "ok";
    }





}