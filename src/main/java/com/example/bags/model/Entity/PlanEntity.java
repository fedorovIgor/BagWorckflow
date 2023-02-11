package com.example.bags.model.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PlanEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
}
