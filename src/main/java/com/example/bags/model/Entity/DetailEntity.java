package com.example.bags.model.Entity;

import com.example.bags.model.Detail;
import jakarta.persistence.*;

@Entity
@Table(name = "detail")
public class DetailEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private  int count;
    private int area;
    private int length;
    private int width;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public BagEntity getBag() {
        return bag;
    }

    public void setBag(BagEntity bag) {
        this.bag = bag;
    }
}
