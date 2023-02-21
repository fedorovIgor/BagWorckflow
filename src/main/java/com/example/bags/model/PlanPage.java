package com.example.bags.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlanPage {
    private List<Plan> plans;
    private Long totalPlans;
    private Integer  totalPages;
}
