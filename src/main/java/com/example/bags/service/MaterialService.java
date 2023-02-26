package com.example.bags.service;

import com.example.bags.dao.MaterialPriseRepository;
import com.example.bags.dao.MaterialRepository;
import com.example.bags.exception.ServiceRuntimeException;
import com.example.bags.model.Entity.MaterialEntity;
import com.example.bags.model.Entity.MaterialPriseEntity;
import com.example.bags.model.Material;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private  final MaterialPriseRepository materialPriseRepository;

    public List<Material> getMaterials() {

        var result = new ArrayList<Material>();

        materialRepository.findAll().forEach(e -> {
            var m = new Material(e);
            result.add(m);
        });

        return result;
    }

    public Material addNewMaterial(Material material) {

        if (material.getName() == null || material.getCount() == null)
            throw new ServiceRuntimeException("need more information in material: " + material);

        var  optionalEntity = this.materialRepository.findByName(material.getName());
        if (optionalEntity.isPresent())
            throw new ServiceRuntimeException("material is already present: " + material);

        var materialEntity = new MaterialEntity(material);

        this.materialRepository.save(materialEntity);

        material.setId(materialEntity.getId());

        return material;
    }

    @Transactional
    public Material updateMaterialByName(Material material) {

        var  entity = this.materialRepository.findByName(material.getName())
                .orElseThrow(()  -> new ServiceRuntimeException("can not find material by name: " + material));

        entity.setCount(material.getCount());

        return new Material(entity);
    }

    public Material updateMaterialPrice(Material material) {

        if (material == null || material.getMaterialPrice() == null )
            throw new ServiceRuntimeException("incorrect value in price: " + material);

        var  materialEntity = this.materialRepository.findById(material.getId())
                .orElseThrow(()  -> new ServiceRuntimeException("can not find material by id: " + material));

        var materialPrice = new MaterialPriseEntity();
        materialPrice.setPrice(material.getMaterialPrice());

        materialPriseRepository.save(materialPrice);

        materialEntity.setMaterialPrise(materialPrice);
        materialRepository.save(materialEntity);

        return material;
    }
}
