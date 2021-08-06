package com.example.asyncdemo.controller;

import com.example.asyncdemo.entity.TestEntity;
import com.example.asyncdemo.service.TestEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestEntityController {

    @Autowired
    TestEntityService testEntityService;

    @PostMapping
    Mono<TestEntity> save(@RequestBody TestEntity testEntity) {
        return testEntityService.save(testEntity);
    }

    @GetMapping("/{id}")
    Mono<TestEntity> get(@PathVariable Long id) {
        return testEntityService.get(id);
    }

    @GetMapping("/do/async")
    Mono<String> doSomeThingAsync() {
        return testEntityService.doSomeThingAsync();
    }

    @GetMapping("/do")
    Mono<String> doSomeThing() {
        return testEntityService.doSomeThing();
    }

    @PatchMapping("/{id}")
    Mono<TestEntity> update(@PathVariable Long id) {
        return testEntityService.update(id);
    }

    @GetMapping
    Flux<TestEntity> getAll() {
        return testEntityService.getAll();
    }
    
    @GetMapping("/health")
    Mono<String> getHealth() {
        return Mono.just("WORKING");
    }
}
