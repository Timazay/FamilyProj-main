package com.example.familyproj.test.service;

import com.example.familyproj.test.entity.Child;
import com.example.familyproj.test.entity.state.ChildDay;
import com.example.familyproj.test.entity.state.ChildEvent;
import com.example.familyproj.test.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChildDayService {
    @Autowired
    private ChildRepository repository;
  @Autowired
    private StateMachine<ChildDay, ChildEvent> sm;
    @Autowired
    private ChildDayChangeInterceptor childDayChangeInterceptor;

    public Child newDay(Child child) {
        child.setDay(ChildDay.NEW);
        return repository.save(child);
    }

    @Transactional
    public StateMachine<ChildDay, ChildEvent> goToSchool(Long id) {
        StateMachine<ChildDay, ChildEvent> sm = build(id);

        sendEvent(id, sm, ChildEvent.GOING_TO_SCHOOL);
        return sm;
    }

    private void sendEvent(Long paymentId, StateMachine<ChildDay, ChildEvent> sm, ChildEvent event) {
        Message msg = MessageBuilder.withPayload(event)
                .setHeader(event.name(), paymentId)
                .build();

        sm.sendEvent(msg);
    }

    private StateMachine<ChildDay, ChildEvent> build(Long paymentId) {
        Child child = repository.getOne(paymentId);

        StateMachine<ChildDay, ChildEvent> stateMachine = sm;


        stateMachine.stop();

        stateMachine.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(childDayChangeInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(child.getDay(), null, null, null));
                });

        stateMachine.start();

        return stateMachine;
    }
}
