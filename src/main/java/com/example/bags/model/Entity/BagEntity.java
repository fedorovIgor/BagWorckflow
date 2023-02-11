package com.example.bags.model.Entity;

import com.example.bags.model.Bag;
import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.util.List;

@Entity
public class BagEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer vendorCode;

    @OneToMany(mappedBy = "bag")
    private List<DetailEntity> details;

    public BagEntity() {
    }

    public BagEntity (Bag bag) {
        this.name = bag.getName();
        this.vendorCode = bag.getVendorCode();
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

    public Integer getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(Integer vendorCode) {
        this.vendorCode = vendorCode;
    }

    public List<DetailEntity> getDetails() {
        return details;
    }

    public void setDetails(List<DetailEntity> details) {
        this.details = details;
    }
}
