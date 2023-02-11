package com.example.bags.model;

public class SheetDetail {
    private int id;
    private int detailId;
    private String materialName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Override
    public String toString() {
        return "SheetDetail{" +
                "id=" + id +
                ", detailId=" + detailId +
                ", materialName='" + materialName + '\'' +
                '}';
    }
}
