package com.example.bags.model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "plan_info")
public class PlanInfoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private Integer count;

    @ManyToOne
    @JoinColumn(name="bag_id")
    private BagEntity bag;

    @ManyToOne
    @JoinColumn(name="plan_id")
    private PlanEntity plan;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BagEntity getBag() {
        return bag;
    }

    public void setBag(BagEntity bag) {
        this.bag = bag;
    }

    public PlanEntity getPlan() {
        return plan;
    }

    public void setPlan(PlanEntity plan) {
        this.plan = plan;
    }
}
