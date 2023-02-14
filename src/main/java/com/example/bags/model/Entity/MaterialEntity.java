package com.example.bags.model.Entity;

import com.example.bags.model.Material;
import jakarta.persistence.*;

@Entity
@Table(name = "material")
public class MaterialEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private int count;

    public MaterialEntity() {
    }

    public MaterialEntity(Material material) {
        this.name = material.getName();
        this.count = material.getCount();
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
