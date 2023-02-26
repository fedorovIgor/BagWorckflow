package com.example.bags.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "material_price")
@Getter
@Setter
@ToString
public class MaterialPriseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private BigDecimal price;

    @OneToOne
    private MaterialEntity material;
}
