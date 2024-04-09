package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Subscription;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.SubscriptionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/subscription/")

public class SubscriptionRestController {
    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @RequestMapping("/hello")
    public String HelloWorld (){
        return "This is a sandbox for my first java class controller";
    }

    //CRUD: read
    @RequestMapping("/subscriptions")
    public Iterable<Subscription> getAllSubscription (){

        return subscriptionRepository.findAll();

    }

    //CRUD: delete
    @DeleteMapping("/delete")
    public String deleteSubscription(@RequestParam String subscriptionId) {

        //System.out.println("id:" + id);
        Optional<Subscription> subscriptionFound = subscriptionRepository.findById(subscriptionId);
        long countBefore = subscriptionRepository.count();
        //System.out.println("subscriptionFound:" + subscriptionFound);

        if (subscriptionFound.isPresent()){
            subscriptionRepository.deleteById(subscriptionId);
            long countAfter = subscriptionRepository.count();
            String response = "subscription deleted: " + subscriptionId + " count: " + countBefore + "/" + countAfter;
            return response;
        } else return "id: " + subscriptionId +  " not found " + " count: " + countBefore;

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
    public Subscription updateSubscription (@PathVariable String subscriptionId, @RequestBody Subscription subscription) {

        Optional<Subscription> subscriptionFound = subscriptionRepository.findById(subscriptionId);

        if (subscriptionFound.isPresent()) {
            Subscription subscriptionToUpdate = subscriptionFound.get();
            //
            if  (subscription.getSubscriptionModality() > 0) {
                subscriptionToUpdate.setSubscriptionModality(subscription.getSubscriptionModality());
            }

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
