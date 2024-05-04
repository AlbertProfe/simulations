package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    private String id;
    private String player;
    private int age;
    private boolean active;

    private List<String> imagePlayerIds = new ArrayList<>();

    public void addImagePlayerId(String imagePlayerId){
        this.getImagePlayerIds().add(imagePlayerId);
    }

    
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Simulation> simulations = new ArrayList<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Subscription> subscriptions = new ArrayList<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Social> socials = new ArrayList<>();

    @OneToMany(targetEntity= Enrollment.class, mappedBy = "player", cascade = CascadeType.ALL,  fetch= FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();

    public void addEnrollment(Enrollment enrollment) {
        this.getEnrollments().add(enrollment);
        enrollment.setPlayer(this);
    }

    public void addSimulation(Simulation simulation) {
        this.getSimulations().add(simulation);
        //if (simulation.getId() != null) simulation.getId().getSimulations().remove(simulation);
        simulation.setPlayer(this);
    }
    public void addSocial(Social social) {
        this.getSocials().add(social);
        social.setPlayer(this);
    }

    public void addCard(Card card) {
        this.getCards().add(card);
        card.setPlayer(this);
    }

    public void addPayment(Payment payment) {
        this.getPayments().add(payment);
        //if (simulation.getId() != null) simulation.getId().getSimulations().remove(simulation);
        payment.setPlayer(this);
    }

    public void addSubscription(Subscription subscription) {
        this.getSubscriptions().add(subscription);
        //if (subscription.getId() != null) subscription.getId().getSubscriptions().remove(subscription);
        subscription.setPlayer(this);
    }
}
