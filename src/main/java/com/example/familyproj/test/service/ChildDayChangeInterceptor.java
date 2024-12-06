package com.example.familyproj.test.service;

import com.example.familyproj.test.entity.Child;
import com.example.familyproj.test.entity.state.ChildDay;
import com.example.familyproj.test.entity.state.ChildEvent;
import com.example.familyproj.test.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChildDayChangeInterceptor extends StateMachineInterceptorAdapter<ChildDay, ChildEvent> {

    @Autowired
    private ChildRepository childRepository;


    public void preStateChange(State<ChildDay, ChildEvent> state, Message<ChildEvent> message,
                               Transition<ChildDay, ChildEvent> transition, StateMachine<ChildDay, ChildEvent> stateMachine) {

        Optional.ofNullable(message).ifPresent(msg -> {
            Optional.ofNullable(Long.class.cast(msg.getHeaders().getOrDefault(ChildService.CHILD_ID_HEADER, -1L)))
                    .ifPresent(paymentId -> {
                        Child child = childRepository.getOne(paymentId);
                        child.setDay(state.getId());
                        childRepository.save(child);
                    });
        });
    }
}
