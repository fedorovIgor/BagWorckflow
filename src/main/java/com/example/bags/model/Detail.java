package com.example.bags.model;

import com.example.bags.model.Entity.DetailEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class Detail {

    private Integer id;
    private String name;
    private  int count;
    private BigDecimal area;
    private BigDecimal length;
    private BigDecimal width;

    public Detail() {
    }

    public Detail(DetailEntity e) {
        this.name = e.getName();
        this.id = e.getId();
        this.count = e.getCount();
        this.area = e.getArea();
        this.length = e.getLength();
        this.width = e.getWidth();
    }


    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", area=" + area +
                ", length=" + length +
                ", width=" + width +
                '}';
    }
}
