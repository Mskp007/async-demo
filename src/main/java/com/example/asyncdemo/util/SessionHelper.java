package com.example.asyncdemo.util;

import com.example.asyncdemo.entity.TestEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import reactor.core.publisher.Mono;

@Component
@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionHelper {
    private String name;
    private Mono<TestEntity> entity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mono<TestEntity> getEntity() {
        return entity;
    }

    public void setEntity(Mono<TestEntity> entity) {
        this.entity = entity;
    }
}
