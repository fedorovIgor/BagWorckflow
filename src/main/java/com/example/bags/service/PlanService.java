package com.example.bags.service;

import com.example.bags.model.Plan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {

    private final List<Plan> plans = new ArrayList<>();
    private Integer id = 0;

    public void savePlan(Plan plan) {
        this.plans.add(plan);
    }

    public List<Plan> getPlans() {
        return this.plans;
    }
}
