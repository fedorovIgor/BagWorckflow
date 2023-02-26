package com.example.bags.model;

import com.example.bags.model.Entity.MaterialEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class Material {

    private int id;
    private String name;
    @JsonProperty("balance")
    private Integer count;
    private BigDecimal materialPrice;

    public Material() {
    }

    public Material(MaterialEntity e) {
        this.id = e.getId();
        this.name = e.getName();
        this.count = e.getCount();
        if (e.getMaterialPrise() != null)
            this.materialPrice  = e.getMaterialPrise().getPrice();
    }
}
