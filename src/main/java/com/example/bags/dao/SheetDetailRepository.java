package com.example.bags.dao;

import com.example.bags.model.Entity.MaterialEntity;
import com.example.bags.model.Entity.SheetDetailEntity;
import com.example.bags.model.SheetDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SheetDetailRepository extends CrudRepository<SheetDetailEntity, Integer> {
    String SELECT_BY_PLAN_INFO_Q = """
            SELECT s
            FROM SheetDetailEntity s
            WHERE s.planInfo = ?1""";

    @Query(SELECT_BY_PLAN_INFO_Q)
    List<SheetDetailEntity> findByPlanInfoId(Integer planInfoId);
}
