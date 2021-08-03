package com.example.asyncdemo.repo;

import com.example.asyncdemo.entity.TestEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TestEntityRepo extends R2dbcRepository<TestEntity, Long> {
}
