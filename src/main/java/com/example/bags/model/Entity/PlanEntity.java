package com.example.bags.model.Entity;

import com.example.bags.model.PlanInfo;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "plan")
public class PlanEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private LocalDateTime date;

    @OneToMany(mappedBy = "plan")
    private List<PlanInfoEntity> plansInfo;

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

    public List<PlanInfoEntity> getPlansInfo() {
        return plansInfo;
    }

    public void setPlansInfo(List<PlanInfoEntity> plansInfo) {
        this.plansInfo = plansInfo;
    }
}
