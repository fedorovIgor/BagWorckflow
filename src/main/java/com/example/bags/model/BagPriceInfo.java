package com.example.bags.model;

import com.example.bags.model.Entity.PlanInfoEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class BagPriceInfo {

    private Integer bagId;
    private String bagName;
    private Integer BagsCount;
    private List<MaterialConsumption> MaterialsConsumption;
    private BigDecimal bagPrice;
    private BigDecimal totalPrice;

    public BagPriceInfo() {
    }

    public BagPriceInfo(PlanInfoEntity entity) {
        this.bagId = entity.getBag().getId();
        this.bagName = entity.getBag().getName();
        this.BagsCount = entity.getCount();
    }

}
