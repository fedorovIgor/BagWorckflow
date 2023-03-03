package com.example.bags.model;

import com.example.bags.model.Entity.SheetDetailEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CuttingSheetDetail {

    private int count;
    private int countTotal;
    private String materialName;
    private String detailName;
    private String comment;
    private BigDecimal area;
    private BigDecimal length;
    private BigDecimal width;

    public CuttingSheetDetail() {
    }

    public CuttingSheetDetail(SheetDetailEntity entity) {
        this.count = entity.getDetail().getCount();
        this.materialName = entity.getMaterial().getName();
        this.detailName = entity.getDetail().getName();
        this.detailName = entity.getDetail().getName();
        this.area = entity.getDetail().getArea();
        this.length = entity.getDetail().getLength();
        this.width = entity.getDetail().getWidth();
        this.comment = entity.getComment();
    }


}
