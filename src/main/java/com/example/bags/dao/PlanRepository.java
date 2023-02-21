package com.example.bags.dao;

import com.example.bags.model.Entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<PlanEntity, Integer> {
}
