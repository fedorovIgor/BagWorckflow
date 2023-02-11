package com.example.bags.model;

import java.util.List;

public class PlanInfo {

    private int bagId;
    private String bagName;
    private int count;
    private List<SheetDetail> sheetDetail;

    public int getBagId() {
        return bagId;
    }

    public void setBagId(int bagId) {
        this.bagId = bagId;
    }

    public String getBagName() {
        return bagName;
    }

    public void setBagName(String bagName) {
        this.bagName = bagName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SheetDetail> getSheetDetail() {
        return sheetDetail;
    }

    public void setSheetDetail(List<SheetDetail> sheetDetail) {
        this.sheetDetail = sheetDetail;
    }

    @Override
    public String toString() {
        return "PlanInfo{" +
                "bagId=" + bagId +
                ", bagName='" + bagName + '\'' +
                ", count=" + count +
                ", sheetDetail=" + sheetDetail +
                '}';
    }
}
