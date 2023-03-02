package com.example.bags.model;

import com.example.bags.model.Entity.SheetDetailEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter @Setter
@ToString
public class MaterialConsumption {
    private Integer materialId;
    private String materialName;
    private Integer detailArea;
    private BigDecimal price;
    private BigDecimal totalPrice;

    public MaterialConsumption(SheetDetailEntity e) {
        this.materialId = e.getMaterial().getId();
        this.materialName = e.getMaterial().getName();
        this.detailArea = e.getDetail().getArea();
        this.price = e.getMaterial().getMaterialPrise().getPrice();
        calculateTotalPrice();
    }

    public void addArea(Integer area) {
        this.detailArea += area;
    }

    public void calculateTotalPrice() {
        this.totalPrice = this.price.multiply(new BigDecimal(detailArea));
    }
}
