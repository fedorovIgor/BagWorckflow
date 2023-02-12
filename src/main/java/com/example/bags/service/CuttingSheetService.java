package com.example.bags.service;

import com.example.bags.dao.PlanIfoRepository;
import com.example.bags.dao.SheetDetailRepository;
import com.example.bags.model.CuttingSheet;
import com.example.bags.model.CuttingSheetDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CuttingSheetService {

    private final PlanIfoRepository planIfoRepository;
    private final SheetDetailRepository sheetDetailRepository;

    public CuttingSheet getSheetById(Integer planInfoId) {
        var planInfoEntity = this.planIfoRepository.findById(planInfoId)
                .orElseThrow(() -> new RuntimeException("cant find by id: " + planInfoId));

        var cuttingSheet = new CuttingSheet(planInfoEntity);

        var sheetDetails = sheetDetailRepository.findByPlanInfoId(planInfoId).stream()
                .map(e -> {
                    var result = new CuttingSheetDetail(e);
                    result.setCountTotal(result.getCount() * cuttingSheet.getCount());
                    return result;
                })
                .toList();

        cuttingSheet.setDetails(sheetDetails);

        return cuttingSheet;
    }
}
