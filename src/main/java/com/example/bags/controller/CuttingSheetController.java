package com.example.bags.controller;

import com.example.bags.model.CuttingSheet;
import com.example.bags.service.CuttingSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.1.100"}, maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class CuttingSheetController {

    private final CuttingSheetService cuttingSheetService;

    @GetMapping("api/v1/sheet/{planInfoId}")
    public CuttingSheet getSheetById(@PathVariable Integer planInfoId) {
        return this.cuttingSheetService.getSheetById(planInfoId);
    }

    @GetMapping("api/v1/position-status")
    public List<String> getStatuses() {
        return this.cuttingSheetService.getPositionStatuses();
    }

}
