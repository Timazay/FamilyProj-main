package com.example.familyproj.test.config;


import com.example.familyproj.test.builder.DayStateMachineBuilder;
import com.example.familyproj.test.entity.state.ChildDay;
import com.example.familyproj.test.entity.state.ChildEvent;
import com.example.familyproj.test.service.ChildService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.*;

import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Random;


@Slf4j
/*@EnableStateMachineFactory*/
/*@EnableStateMachine*/
@Configuration
public class ChildStateMachineConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Bean
    public StateMachine<ChildDay, ChildEvent> stateMachine(String s) throws Exception {
        StateMachineBuilder.Builder<ChildDay, ChildEvent> builder = StateMachineBuilder.builder();

        builder.configureStates()
                .withStates()
                .initial(ChildDay.NEW)
                .state(ChildDay.WEEKEND)
                .state(ChildDay.WEEKDAY)
                .end(ChildDay.END);
        DayStateMachineBuilder builder1 = new DayStateMachineBuilder();



        builder.configureTransitions()
                .withExternal()
                .source(ChildDay.NEW).target(ChildDay.WEEKDAY)
                .event(ChildEvent.GOING_TO_SCHOOL)

                .and()
                .withExternal()
                .source(ChildDay.NEW).target(ChildDay.WEEKDAY)
                .event(ChildEvent.GOING_TO_SCHOOL)

                .and()
                .withExternal()
                .source(ChildDay.WEEKDAY).target(ChildDay.END)
                .event(ChildEvent.COMPLETE)

                .and()
                .withExternal()
                .source(ChildDay.NEW).target(ChildDay.WEEKEND)
                .event(ChildEvent.PLAYING_VIDEO_GAMES)

                .and()
                .withExternal()
                .source(ChildDay.NEW).target(ChildDay.WEEKEND)
                .event(ChildEvent.LEISURE_ACTIVITY)

                .and()
                .withExternal()
                .source(ChildDay.NEW).target(ChildDay.WEEKEND)
                .event(ChildEvent.DOING_HOMEWORK)

                .and()
                .withExternal()
                .source(ChildDay.WEEKEND).target(ChildDay.END)
                .event(ChildEvent.COMPLETE);

        StateMachine<ChildDay, ChildEvent> stateMachine = builder.build();
        stateMachine.addStateListener(listener());
        return stateMachine;
    }

    public Action<ChildDay, ChildEvent> goToSchool(){
        return context -> {
                System.out.println("Approved");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(ChildEvent.GOING_TO_SCHOOL)
                        .setHeader(ChildService.CHILD_ID_HEADER, context.getMessageHeader(ChildService.CHILD_ID_HEADER))
                        .build());

        };
    }
    @Bean
    public StateMachineListener<ChildDay, ChildEvent> listener() {
        return new StateMachineListenerAdapter<ChildDay, ChildEvent>() {
            @Override
            public void stateChanged(State<ChildDay, ChildEvent> from, State<ChildDay, ChildEvent> to) {
                logger.info("State change from " + from.getId() + "to " + to.getId());
            }
        };
    }

}
