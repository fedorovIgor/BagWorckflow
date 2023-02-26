package com.example.bags.model.Entity;

import com.example.bags.model.Material;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "material")
@Getter @Setter
public class MaterialEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private int count;

    @OneToOne
    @JoinColumn(name = "material_price_id", referencedColumnName = "id")
    private MaterialPriseEntity  materialPrise;

    public MaterialEntity() {
    }

    public MaterialEntity(Material material) {
        this.name = material.getName();
        this.count = material.getCount();
    }

}
