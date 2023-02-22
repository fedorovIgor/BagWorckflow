package com.example.bags.model.Entity;

import com.example.bags.model.PositionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "plan_info")
@Getter
@Setter
public class PlanInfoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private Integer count;

    @ManyToOne
    @JoinColumn(name="material_id")
    private MaterialEntity material;

    @ManyToOne
    @JoinColumn(name="bag_id")
    private BagEntity bag;

    @ManyToOne
    @JoinColumn(name="plan_id")
    private PlanEntity plan;

    @Enumerated(EnumType.STRING)
    private PositionStatus positionStatus;

    @Override
    public String toString() {
        return "PlanInfoEntity{" +
                "id=" + id +
                ", count=" + count +
                ", bag=" + bag +
                ", plan=" + plan +
                ", positionStatus=" + positionStatus +
                '}';
    }
}
