package com.example.bags.model;

import java.time.LocalDateTime;
import java.util.List;

public class Plan {
    private LocalDateTime date;
    private List<PlanInfo> planInfo;

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
