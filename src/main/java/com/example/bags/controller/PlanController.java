package com.example.bags.controller;

import com.example.bags.model.Plan;
import com.example.bags.model.PlanInfo;
import com.example.bags.model.PlanPage;
import com.example.bags.service.PlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.1.100"}, maxAge = 3600)
@RestController
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping("api/v1/plan")
    public void addNewPlanInfo(@RequestBody Plan plan) {
        this.planService.savePlan(plan);
    }


    @GetMapping("api/v1/plan")
    public PlanPage getAllPlans(@RequestParam Integer page,
                                @RequestParam Integer size) {
        return this.planService.getAllPlans(page, size);
    }

    @GetMapping("api/v1/plan/{id}")
    public Plan getPlanById(@PathVariable Integer id) {
        return this.planService.getPlanById(id);
    }
}
