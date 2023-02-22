package com.example.bags.dao;

import com.example.bags.model.Entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<PlanEntity, Integer> {

    String SELECT_MATERIAL_IN_PLAN = """
            SELECT SUM(area) * SUM(d.count) AS value, ma.name AS material
            FROM plan pl
                JOIN plan_info pi ON pi.plan_id = pl.id
                JOIN sheet_detail s ON s.plan_info_id = pi.id
                JOIN material ma ON ma.id = s.material_id
                JOIN detail d ON d.id = s.detail_id_id
            WHERE pl.id = ?1
            GROUP BY ma.name """;


}
