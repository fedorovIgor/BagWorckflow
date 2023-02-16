package com.example.bags.service;

import com.example.bags.dao.MaterialRepository;
import com.example.bags.exception.ServiceRuntimeException;
import com.example.bags.model.Entity.MaterialEntity;
import com.example.bags.model.Material;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }


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
}
