package com.example.bags.service;

import com.example.bags.dao.PlanIfoRepository;
import com.example.bags.dao.SheetDetailRepository;
import com.example.bags.exception.ServiceRuntimeException;
import com.example.bags.model.*;
import com.example.bags.model.Entity.PlanInfoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public BagPriceInfo getBagPriceById(Integer sheetId) {

        var planInfoEntity = this.planIfoRepository.findById(sheetId)
                .orElseThrow(() -> new ServiceRuntimeException("cant find planInfo by id: " + sheetId));

        var materialConsumptions = sheetDetailRepository.findByPlanInfoId(planInfoEntity).stream()
                .map(e -> new MaterialConsumption(e) )
                .toList();

        var mapMaterial = new HashMap<String, MaterialConsumption>();
        for (var c : materialConsumptions) {
            String key = c.getMaterialName();

            if (mapMaterial.containsKey(key)) {
                var temp = mapMaterial.get(key);
                temp.addArea(c.getDetailArea());
                temp.calculateTotalPrice();

                mapMaterial.put(key, temp);
            } else {
                mapMaterial.put(key, c);
            }

        }

        var materialList = mapMaterial.entrySet().stream()
                .map(Map.Entry::getValue)
                .toList();

        var bagPrice = new BagPriceInfo(planInfoEntity);
        bagPrice.setMaterialsConsumption(materialList);

        var totalBagPrice = mapMaterial.entrySet().stream()
                .map(Map.Entry::getValue)
                .map(MaterialConsumption::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        bagPrice.setBagPrice(totalBagPrice);
        bagPrice.setTotalPrice(totalBagPrice.multiply(new BigDecimal( bagPrice.getBagsCount())));

        return bagPrice;
    }
}
