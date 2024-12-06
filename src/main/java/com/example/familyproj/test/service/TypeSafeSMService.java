package com.example.familyproj.test.service;

import com.example.familyproj.test.builder.DayStateMachineBuilder;
import com.example.familyproj.test.entity.Child;
import com.example.familyproj.test.entity.state.ChildDay;
import com.example.familyproj.test.entity.state.ChildEvent;
import com.typesafe.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Service;

import java.io.ObjectInputFilter;

@Service
public class TypeSafeSMService {
    @Autowired
    private DayStateMachineBuilder builder;
    @Autowired
    private ChildService childService;

    public StateMachine<ChildDay, ChildEvent> receiveSM(Config config) throws Exception {
        Child child = childService.findById(config.getLong("id"));
        builder.build(config.getString("source"), config.getString("target"), config.getString("event"),);
        return builder.buildMachine();

    }
}
