package com.example.bags.dao;

import com.example.bags.model.Entity.BagEntity;
import org.springframework.data.repository.CrudRepository;

public interface BagRepository extends CrudRepository<BagEntity, Integer> {
}
