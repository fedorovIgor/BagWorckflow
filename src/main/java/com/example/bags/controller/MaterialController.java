package com.example.bags.controller;

import com.example.bags.model.Material;
import com.example.bags.service.MaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.1.100"}, maxAge = 3600)
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
}
