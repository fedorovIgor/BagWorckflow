package com.example.bags.controller;

import com.example.bags.model.CuttingSheet;
import com.example.bags.service.CuttingSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PutMapping("api/v1/sheet")
    public CuttingSheet updateStatus(@RequestBody CuttingSheet cuttingSheet) {
        return this.cuttingSheetService.updatePositionStatus(cuttingSheet);
    }

}
