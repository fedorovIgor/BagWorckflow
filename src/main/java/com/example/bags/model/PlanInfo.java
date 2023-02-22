package com.example.bags.model;

import com.example.bags.model.Entity.PlanInfoEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PlanInfo {

    private int id;
    private int bagId;
    private String bagName;
    private String materialName;
    private int count;
    private List<SheetDetail> sheetDetails;
    private PositionStatus positionStatus;

    public PlanInfo() {
    }

    public PlanInfo(PlanInfoEntity entity) {
        this.bagId = entity.getBag().getId();
        this.bagName = entity.getBag().getName();
        this.count = entity.getCount();
        this.id = entity.getId();
        this.positionStatus = entity.getPositionStatus();
        this.materialName = entity.getMaterial().getName();
    }

}
