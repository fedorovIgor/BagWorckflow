package com.example.bags.model;

import com.example.bags.model.Entity.BagEntity;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    private int id;
    private String name;
    private Integer vendorCode;
    private List<Detail> details;

    public Bag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bag(BagEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.vendorCode = entity.getVendorCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(Integer vendorCode) {
        this.vendorCode = vendorCode;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> detail) {
        this.details = detail;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "name='" + name + '\'' +
                ", detail=" + details +
                '}';
    }
}
