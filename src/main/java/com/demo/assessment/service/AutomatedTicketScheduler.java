package com.demo.liquibase.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutomatedTicketScheduler {

    @Scheduled(fixedDelay = 3000L)
    private void runScheduler() {


    }
}
