package com.example.bags.model;

import com.example.bags.model.Entity.PlanEntity;

import java.time.LocalDateTime;
import java.util.List;

public class Plan {
    private int id;
    private LocalDateTime date;
    private List<PlanInfo> planInfo;

    public Plan() {
    }

    public Plan(PlanEntity e) {
        this.id = e.getId();
        this.date = e.getDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<PlanInfo> getPlanInfo() {
        return planInfo;
    }

    public void setPlanInfo(List<PlanInfo> planInfo) {
        this.planInfo = planInfo;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "date=" + date +
                ", planInfo=" + planInfo +
                '}';
    }
}
