package com.example.bags.dao;


import com.example.bags.model.Entity.MaterialEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MaterialRepository extends CrudRepository<MaterialEntity, Integer> {
    String SELECT_BY_NAME_Q = """
            SELECT m 
            FROM MaterialEntity m
            WHERE m.name  =  ?1 """;

    @Query(SELECT_BY_NAME_Q)
    Optional<MaterialEntity> findByName(String name);

    String UPDATE_COUNT_BY_NAME_Q = """
            UPDATE MaterialEntity m 
            SET m.count = :count
            WHERE m.name = :name""";

    @Modifying
    @Query(UPDATE_COUNT_BY_NAME_Q)
    void updateByName(@Param("count") Integer status,
                      @Param("name") String name);
}
