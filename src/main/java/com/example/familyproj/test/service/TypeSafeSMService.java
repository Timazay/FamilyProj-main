package com.example.familyproj.test.service;

import com.example.familyproj.test.builder.ActionVisitor;
import com.example.familyproj.test.builder.DayStateMachineBuilder;
import com.example.familyproj.test.entity.Child;
import com.example.familyproj.test.entity.state.ChildDay;
import com.example.familyproj.test.entity.state.ChildEvent;
import com.typesafe.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;


@Service
public class TypeSafeSMService {
    @Autowired
    private DayStateMachineBuilder builder;
    @Autowired
    private ChildService childService;

    public StateMachine<ChildDay, ChildEvent> receiveSM(Config config) throws Exception {
        Child child = childService.findById(config.getLong("input.id"));
        ActionVisitor ac = new ActionVisitor();
        ChildDay source = ChildDay.valueOf(config.getString("input.source"));
        ChildDay target = ChildDay.valueOf(config.getString("input.target"));
        ChildEvent event = ChildEvent.valueOf(config.getString("input.event"));

        builder.build(source, target, event, ac.action(event));
        return builder.buildMachine();
    }

}
