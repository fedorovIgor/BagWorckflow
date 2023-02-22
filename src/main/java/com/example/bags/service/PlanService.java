package com.example.bags.service;

import com.example.bags.dao.*;
import com.example.bags.exception.ServiceRuntimeException;
import com.example.bags.model.*;
import com.example.bags.model.Entity.BagEntity;
import com.example.bags.model.Entity.PlanEntity;
import com.example.bags.model.Entity.PlanInfoEntity;
import com.example.bags.model.Entity.SheetDetailEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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
                .orElseThrow(() ->  new ServiceRuntimeException("cant find material by name: " + sheetDetail.getMaterialName()
                    + " in planInfo: " + planInfoEntity));

        var detailEntity = this.detailRepository.findById(sheetDetail.getDetailId())
                .orElseThrow(() -> new ServiceRuntimeException("cant find detail by id: " + sheetDetail.getDetailId()
                        + " in planInfo: " + planInfoEntity));

        sheetDetailEntity.setDetail(detailEntity);
        sheetDetailEntity.setMaterial(materialEntity);
        sheetDetailEntity.setComment(sheetDetail.getComment());
        sheetDetailEntity.setPlanInfo(planInfoEntity);

        this.sheetDetailRepository.save(sheetDetailEntity);
    }

    private void savePlanInfo(PlanInfo planInfo, PlanEntity plan) {
        var planInfoEntity = new PlanInfoEntity();

        if (planInfo.getSheetDetails() == null)
            throw new ServiceRuntimeException("details in planInfo doesn't present: " + planInfo
                    + " in Plan: " + plan);

        var materialEntity = this.materialRepository.findByName(planInfo.getMaterialName())
                .orElseThrow(() ->  new ServiceRuntimeException("cant find material by name: " + planInfo.getMaterialName()
                        + " in planInfo: " + planInfo));

        var bagEntity = this.bagRepository.findById(planInfo.getBagId())
                .orElseThrow(() -> new ServiceRuntimeException("cant find bag with id: " + planInfo.getBagId()));

        if (bagEntity.getDetails().size() != planInfo.getSheetDetails().size())
            throw new ServiceRuntimeException("details not matched, in bag: " + bagEntity.getDetails().size()
                    + " in planInfo: " + planInfo.getSheetDetails().size()
                    + " in Plan: " + plan);

        this.checkBagDetails(planInfo, bagEntity);

        if (planInfo.getCount() <= 0)
            throw new ServiceRuntimeException("count in planInfo cant be null or less 0: " + planInfo);

        planInfoEntity.setCount(planInfo.getCount());

        if (planInfo.getPositionStatus() == null)
            planInfoEntity.setPositionStatus(PositionStatus.PLANNED);
        else
            planInfoEntity.setPositionStatus(planInfo.getPositionStatus());

        planInfoEntity.setBag(bagEntity);
        planInfoEntity.setPlan(plan);
        planInfoEntity.setMaterial(materialEntity);

        this.planIfoRepository.save(planInfoEntity);

        for (var i : planInfo.getSheetDetails()) {
            this.addDetailInfo(i, planInfoEntity);
        }
    }

    private void checkBagDetails(PlanInfo planInfo, BagEntity bagEntity) {
        var correctDetailsId = new HashSet<Integer>();

        for (var d : bagEntity.getDetails()) {
            correctDetailsId.add(d.getId());
        }

        for (var detail : planInfo.getSheetDetails()) {
            if (!correctDetailsId.contains(detail.getDetailId()))
                throw new ServiceRuntimeException("this plan info contains incorrect details id: " + detail.getDetailId()
                        + " correct details id: " + correctDetailsId );
            else
                correctDetailsId.remove(detail.getDetailId());
        }
    }

    @Transactional
    public void savePlan(Plan plan) {
        var planEntity  = new PlanEntity();

        if  (plan.getPlanInfo() == null || plan.getPlanInfo().size() == 0)
            throw new ServiceRuntimeException("plan is empty: " + plan);

        if (plan.getDate() == null)
            planEntity.setDate(LocalDateTime.now());
        else
            planEntity.setDate(plan.getDate());

        this.planRepository.save(planEntity);

        for (var i : plan.getPlanInfo())
            this.savePlanInfo(i, planEntity);
    }

    public PlanPage getAllPlans(Integer page, Integer size) {

        List<Plan> plans = new ArrayList<>();

        var pageable = this.planRepository.findAll(PageRequest.of(
                page,
                size,
                Sort.by("date").descending()
        ));

        pageable.forEach(planEntity -> {
            var plan = new Plan(planEntity);

            var info = planEntity.getPlansInfo().stream()
                    .map(e -> new PlanInfo(e))
                    .toList();

            plan.setPlanInfo(info);

            plans.add(plan);
        });

        var planPage = new PlanPage();
        planPage.setTotalPlans(pageable.getTotalElements());
        planPage.setTotalPages(pageable.getTotalPages());
        planPage.setPlans(plans);

        return planPage;
    }

    public Plan getPlanById(Integer planId) {

        var planEntity = this.planRepository.findById(planId)
                .orElseThrow(() -> new ServiceRuntimeException("cant find plan by id: " + planId));

        var info = planEntity.getPlansInfo().stream()
                .map(e -> new PlanInfo(e))
                .toList();

        var plan = new Plan(planEntity);

        plan.setPlanInfo(info);

        return plan;
    }
}










