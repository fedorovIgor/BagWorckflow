package com.example.bags.model.Entity;

import com.example.bags.model.Detail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "detail")
@Getter @Setter
public class DetailEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private  int count;
    private BigDecimal area;
    private BigDecimal length;
    private BigDecimal width;

    @ManyToOne
    @JoinColumn(name="bag_id", nullable=false)
    private BagEntity bag;

    public DetailEntity() {
    }

    public DetailEntity(Detail detail) {
        this.name = detail.getName();
        this.count = detail.getCount();
        this.area = detail.getArea();
        this.length = detail.getLength();
        this.width = detail.getWidth();
    }

}
