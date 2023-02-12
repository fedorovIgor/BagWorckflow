package com.example.bags.model;

public class SheetDetail {

    private int id;
    private int detailId;
    private String materialName;
    private String comment;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "SheetDetail{" +
                "id=" + id +
                ", detailId=" + detailId +
                ", materialName='" + materialName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
