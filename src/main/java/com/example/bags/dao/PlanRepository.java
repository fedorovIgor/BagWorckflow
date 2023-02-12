package com.example.bags.dao;

import com.example.bags.model.Entity.PlanEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<PlanEntity, Integer> {
}
