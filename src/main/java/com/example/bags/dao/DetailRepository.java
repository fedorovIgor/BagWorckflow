package com.example.bags.dao;

import com.example.bags.model.Entity.BagEntity;
import com.example.bags.model.Entity.DetailEntity;
import org.springframework.data.repository.CrudRepository;

public interface DetailRepository extends CrudRepository<DetailEntity, Integer> {
}
