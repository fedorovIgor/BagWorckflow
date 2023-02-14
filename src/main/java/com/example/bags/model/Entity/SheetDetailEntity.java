package com.example.bags.model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sheet_detail")
public class SheetDetailEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String comment;

    @ManyToOne
    @JoinColumn(name="material_id", nullable=false)
    private MaterialEntity material;

    @ManyToOne
    @JoinColumn(name="detailId_id", nullable=false)
    private DetailEntity detail;

    @ManyToOne
    @JoinColumn(name="plan_info_id")
    private PlanInfoEntity planInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }

    public DetailEntity getDetail() {
        return detail;
    }

    public void setDetail(DetailEntity detail) {
        this.detail = detail;
    }

    public PlanInfoEntity getPlanInfo() {
        return planInfo;
    }

    public void setPlanInfo(PlanInfoEntity planInfo) {
        this.planInfo = planInfo;
    }
}
