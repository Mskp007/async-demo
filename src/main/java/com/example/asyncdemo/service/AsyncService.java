package com.example.asyncdemo.service;

import com.example.asyncdemo.entity.TestEntity;
import com.example.asyncdemo.repo.TestEntityRepo;
import com.example.asyncdemo.util.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class AsyncService {

    @Autowired
    private TestEntityRepo testEntityRepo;

    @Autowired
    private TestEntityService testEntityService;
    @Autowired
    private R2dbcEntityTemplate template;

    @Autowired
    private SessionHelper sessionHelper;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void doSomeThingAsync(Long id, final Mono<TestEntity> testEntityMono) {
        try {
            System.err.println("SLEEP");
            Thread.sleep(10000);
            System.err.println("AWAKE");
            System.err.println(id);
//            RequestContextHolder.getRequestAttributes();
//            sessionHelper.getEntity()
            this.testEntityRepo.findById(id)
                    .flatMap(p -> testEntityMono.map(u -> {
                        p.setEndTime(LocalDateTime.now());
                        return p;
                    }))
                    .flatMap(p -> this.testEntityRepo.save(p));
            testEntityService.update(1L);
            save();
//            testEntityService.save(new TestEntity());
//            this.template.update(
//                    Query.query(Criteria.where
//                            ("id").is(id)),
//                    Update.update("endTime", LocalDateTime.now()),
//                    TestEntity.class
//            );

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    void save() {
        System.out.println("CALLED");
        TestEntity e = new TestEntity();
        e.setName("ASD");
        testEntityRepo.save(e);
    }

}
