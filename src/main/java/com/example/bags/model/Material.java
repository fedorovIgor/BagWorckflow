package com.example.bags.model;

import com.example.bags.model.Entity.MaterialEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Material {

    private int id;
    private String name;
    @JsonProperty("balance")
    private Integer count;

    public Material() {
    }

    public Material(MaterialEntity e) {
        this.id = e.getId();
        this.name = e.getName();
        this.count = e.getCount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
