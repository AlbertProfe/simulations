package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Subscription {

    @Id
    private String id;
    private String subscriptionStartedAt;
    private Boolean subscriptionActive;
    private String payment;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PLAYER_FK")
    private Player player;


}
