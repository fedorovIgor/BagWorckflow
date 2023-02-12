package com.example.bags.controller;

import com.example.bags.model.CuttingSheet;
import com.example.bags.service.CuttingSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CuttingSheetController {

    private final CuttingSheetService cuttingSheetService;

    @GetMapping("api/v1/sheet/{planInfoId}")
    public CuttingSheet getSheetById(@PathVariable Integer planInfoId) {
        return this.cuttingSheetService.getSheetById(planInfoId);
    }

}
