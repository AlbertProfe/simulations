package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Subscription {

    @Id
    private String subscriptionId;
    private String subscriptionDate;
    private int subscriptionModality;
    private boolean subscriptionActive;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PLAYER_SUBS")
    private Player player;


}
