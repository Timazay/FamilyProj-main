package com.example.familyproj.test.config;

import com.example.familyproj.test.entity.Child;
import com.example.familyproj.test.entity.state.ChildDay;
import com.example.familyproj.test.entity.state.ChildEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

/*@Log
@Component
@RequiredArgsConstructor
public class Runner implements ApplicationRunner {
    private final StateMachine<ChildDay, ChildEvent> stateMachine;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        stateMachine.sendEvent(ChildEvent.DOING_HOMEWORK);
        stateMachine.start();

        stateMachine.sendEvent(ChildEvent.GOING_TO_SCHOOL);
        log.info("Current state: " + stateMachine.getState().getId().name());

        log.info("Event: " + stateMachine.sendEvent(ChildEvent.SPORT_ACTIVITY));

        log.info("Current state: " + stateMachine.getState().getId().name());

        *//*Child child = new Child();
        stateMachine.getState();
        System.out.println(child.setDay());*//*
    }
}*/
