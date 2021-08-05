package com.example.asyncdemo.service;

import com.example.asyncdemo.entity.TestEntity;
import com.example.asyncdemo.repo.TestEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class TestEntityService {
    @Autowired
    private TestEntityRepo testEntityRepo;

    @Autowired
    private AsyncService service;

    @Autowired
    private AsyncServiceNew asyncServiceNew;

    @Transactional
    public Mono<TestEntity> save(TestEntity testEntity) {
        Mono<TestEntity> testEntityMono = testEntityRepo.save(testEntity);
        service.doSomeThingAsync(1L, testEntityMono);
//        testEntityMono.block().setEndTime(LocalDateTime.now());
        return testEntityMono;
    }

    public Mono<TestEntity> get(Long id) {
        return testEntityRepo.findById(id);
    }

    public Mono<TestEntity> doSomeThingAsync() {
        LocalDateTime start = LocalDateTime.now();
        System.err.println("STARTED ;; AT : " + start);
        List<CompletableFuture<LocalDateTime>> result = new ArrayList();
        for (int i = 0; i <= 9; i++) {
            result.add(asyncServiceNew.doSomeThingAsync(String.valueOf(i)));
        }
        CompletableFuture.allOf(result.toArray(new CompletableFuture[0])).join();
        LocalDateTime end = LocalDateTime.now();
        System.err.println("EXECUTION TIME :: " + ChronoUnit.SECONDS.between(start, end));
        System.err.println("ENDED ;; AT : " + end);
        String rt = String.valueOf(ChronoUnit.SECONDS.between(start, end))
        return Mono.just(rt);
    }

    public Mono<TestEntity> doSomeThing() {
        LocalDateTime start = LocalDateTime.now();
        System.err.println("STARTED ;; AT : " + start);
        for (int i = 0; i <= 9; i++) {
            asyncServiceNew.doSomeThing(String.valueOf(i));
        }
        LocalDateTime end = LocalDateTime.now();
        System.err.println("EXECUTION TIME :: " + ChronoUnit.SECONDS.between(start, end));
        System.err.println("ENDED ;; AT : " + end);
        String rt = String.valueOf(ChronoUnit.SECONDS.between(start, end))
        return Mono.just(rt);
    }

    @Transactional
    public Mono<TestEntity> update(Long id) {
        return this.testEntityRepo.findById(id)
                .map(p -> {
                    p.setEndTime(LocalDateTime.now());
                    return p;
                })
                .flatMap(p -> this.testEntityRepo.save(p));
//        return testEntityRepo.findById(id);
    }

    public Flux<TestEntity> getAll() {
        return testEntityRepo.findAll();
    }
}
