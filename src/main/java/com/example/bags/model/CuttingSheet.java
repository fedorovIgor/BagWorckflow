package com.example.bags.model;

import com.example.bags.model.Entity.PlanInfoEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CuttingSheet {

    private int id;
    private String bagName;
    private int vendorCode;
    private int count;
    private String materialName;
    private PositionStatus status;
    private List<CuttingSheetDetail> details;

    public CuttingSheet() {
    }

    public CuttingSheet(PlanInfoEntity entity) {
        this.bagName = entity.getBag().getName();
        this.vendorCode = entity.getBag().getVendorCode();
        this.count = entity.getCount();
        this.id = entity.getId();
        this.status = entity.getPositionStatus();
        if (entity.getMaterial() != null)
            this.materialName = entity.getMaterial().getName();
    }
}
