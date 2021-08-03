package com.example.asyncdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncServiceNew {

    @Autowired
    private WebClient webClient;

    @Async
    CompletableFuture<LocalDateTime> doSomeThingAsync(String systemId) {
        System.out.println("STARTED FOR SYSTEM : " + systemId + " ;; AT : " + LocalDateTime.now());
        for (int i = 0; i <= 9; i++) {
            try {
                String response = webClient.get().uri("https://api.zippopotam.us/us/9021" + i).retrieve().bodyToMono(String.class).block();

            } catch (Exception e) {

            }
        }
        System.out.println("COMPLETED FOR SYSTEM : " + systemId + " ;; AT : " + LocalDateTime.now());
        return CompletableFuture.completedFuture(LocalDateTime.now());
    }

    void doSomeThing(String systemId) {
        System.out.println("STARTED FOR SYSTEM : " + systemId + " ;; AT : " + LocalDateTime.now());
        for (int i = 0; i <= 9; i++) {
            try {
                String response = webClient.get().uri("https://api.zippopotam.us/us/9021" + i).retrieve().bodyToMono(String.class).block();

            } catch (Exception e) {

            }
        }
        System.out.println("COMPLETED FOR SYSTEM : " + systemId + " ;; AT : " + LocalDateTime.now());
    }
}
