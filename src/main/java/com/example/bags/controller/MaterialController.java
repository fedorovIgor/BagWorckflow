package com.example.bags.controller;

import com.example.bags.model.Material;
import com.example.bags.service.MaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MaterialController {

    private final MaterialService materialService;

    
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("api/v1/material")
    public List<Material> getAllMaterials() {
        return this.materialService.getMaterials();
    }

    @PostMapping("api/v1/material")
    public Material addMaterial(@RequestBody Material material) {
       return this.materialService.addNewMaterial(material);
    }

    @PutMapping("api/v1/material")
    public Material updateMaterial(@RequestBody Material material) {
        return this.materialService.updateMaterialByName(material);
    }

    @PutMapping("api/v1/material/price")
    public Material updateMMaterialPrice(@RequestBody Material material) {
        return this.materialService.updateMaterialPrice(material);
    }
}
