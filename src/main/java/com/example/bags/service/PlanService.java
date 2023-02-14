package com.example.bags.service;

import com.example.bags.dao.*;
import com.example.bags.model.Entity.PlanEntity;
import com.example.bags.model.Entity.PlanInfoEntity;
import com.example.bags.model.Entity.SheetDetailEntity;
import com.example.bags.model.Plan;
import com.example.bags.model.PlanInfo;
import com.example.bags.model.SheetDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final SheetDetailRepository sheetDetailRepository;
    private final MaterialRepository  materialRepository;
    private final DetailRepository detailRepository;
    private final BagRepository bagRepository;
    private final PlanIfoRepository planIfoRepository;
    private final PlanRepository planRepository;


    private void addDetailInfo(SheetDetail sheetDetail, PlanInfoEntity planInfoEntity ) {
        var  sheetDetailEntity = new SheetDetailEntity();

        var materialEntity = this.materialRepository.findByName(sheetDetail.getMaterialName())
                .orElseThrow(() ->  new RuntimeException("cant find material by name: " + sheetDetail.getMaterialName()));

        var detailEntity = this.detailRepository.findById(sheetDetail.getDetailId())
                .orElseThrow(() -> new RuntimeException("cant find detail by id: " + sheetDetail.getDetailId()));

        sheetDetailEntity.setDetail(detailEntity);
        sheetDetailEntity.setMaterial(materialEntity);
        sheetDetailEntity.setComment(sheetDetail.getComment());
        sheetDetailEntity.setPlanInfo(planInfoEntity);

        this.sheetDetailRepository.save(sheetDetailEntity);
    }

    private void savePlanInfo(PlanInfo planInfo, PlanEntity plan) {
        var planInfoEntity = new PlanInfoEntity();

        planInfoEntity.setCount(planInfo.getCount());

        var bagEntity = this.bagRepository.findById(planInfo.getBagId())
                .orElseThrow(() -> new RuntimeException("cant find bag with id: " + planInfo.getBagId()));

        planInfoEntity.setBag(bagEntity);
        planInfoEntity.setPlan(plan);

        this.planIfoRepository.save(planInfoEntity);

        for (var i : planInfo.getSheetDetails()) {
            this.addDetailInfo(i, planInfoEntity);
        }
    }

    public void savePlan(Plan plan) {
        var planEntity  = new PlanEntity();

        if  (plan.getPlanInfo() == null || plan.getPlanInfo().size() == 0)
            throw new RuntimeException("plan is empty: " + plan);

        if (plan.getDate() == null)
            planEntity.setDate(LocalDateTime.now());
        else
            planEntity.setDate(plan.getDate());

        this.planRepository.save(planEntity);

        for (var i : plan.getPlanInfo())
            this.savePlanInfo(i, planEntity);
    }

    public List<Plan> getAllPlans() {

        List<Plan> plans = new ArrayList<>();

        this.planRepository.findAll().forEach( planEntity -> {
            var plan = new Plan(planEntity);

            var info = planEntity.getPlansInfo().stream()
                    .map(e -> new PlanInfo(e))
                    .toList();

            plan.setPlanInfo(info);

            plans.add(plan);
        });

        return plans;
    }

    public Plan getPlanById(Integer planId) {

        var planEntity = this.planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("cant find plan by id: " + planId));

        var info = planEntity.getPlansInfo().stream()
                .map(e -> new PlanInfo(e))
                .toList();

        var plan = new Plan(planEntity);

        plan.setPlanInfo(info);

        return plan;
    }
}










