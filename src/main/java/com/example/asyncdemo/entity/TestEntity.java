package com.example.asyncdemo.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "TEST_ENTITY")
public class TestEntity implements Serializable {
    @Id
    private Long id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
