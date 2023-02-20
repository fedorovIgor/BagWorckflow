package com.example.bags.service;

import com.example.bags.dao.PlanIfoRepository;
import com.example.bags.dao.SheetDetailRepository;
import com.example.bags.exception.ServiceRuntimeException;
import com.example.bags.model.CuttingSheet;
import com.example.bags.model.CuttingSheetDetail;
import com.example.bags.model.Entity.PlanInfoEntity;
import com.example.bags.model.PositionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CuttingSheetService {

    private final PlanIfoRepository planIfoRepository;
    private final SheetDetailRepository sheetDetailRepository;

    public CuttingSheet getSheetById(int planInfoId) {

        var planInfoEntity = this.planIfoRepository.findById(planInfoId)
                .orElseThrow(() -> new ServiceRuntimeException("cant find planInfo by id: " + planInfoId));

        var cuttingSheet = new CuttingSheet(planInfoEntity);

        var sheetDetails = sheetDetailRepository.findByPlanInfoId(planInfoEntity).stream()
                .map(e -> {
                    var result = new CuttingSheetDetail(e);
                    result.setCountTotal(result.getCount() * cuttingSheet.getCount());
                    return result;
                })
                .toList();

        cuttingSheet.setDetails(sheetDetails);

        return cuttingSheet;
    }

    public List<String> getPositionStatuses() {
        return Stream.of(PositionStatus.values())
                .map(PositionStatus::name)
                .toList();
    }

    public CuttingSheet updatePositionStatus(CuttingSheet cuttingSheet) {

        var status = Arrays.stream(PositionStatus.values())
                .filter(s -> s.equals(cuttingSheet.getStatus()))
                .findFirst()
                .orElseThrow(()-> new ServiceRuntimeException("incorrect status in cuttingSheet: " + cuttingSheet));

        var planInfoEntity = this.planIfoRepository.findById(cuttingSheet.getId())
                .orElseThrow(() -> new ServiceRuntimeException("cant find planInfo by id: " + cuttingSheet));

        planInfoEntity.setPositionStatus(status);
        this.planIfoRepository.save(planInfoEntity);

        return cuttingSheet;
    }
}
