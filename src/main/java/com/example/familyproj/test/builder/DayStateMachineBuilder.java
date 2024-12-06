package com.example.familyproj.test.builder;

import com.example.familyproj.test.entity.state.ChildDay;
import com.example.familyproj.test.entity.state.ChildEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

@Component
public class DayStateMachineBuilder {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public StateMachine<ChildDay, ChildEvent> buildMachine() throws Exception {
        StateMachineBuilder.Builder<ChildDay, ChildEvent> builder = StateMachineBuilder.builder();


        builder.configureConfiguration()
                .withConfiguration()
                .listener(listener())
                .autoStartup(true);

        builder.configureStates()
                .withStates()
                .initial(ChildDay.NEW)
                .state(ChildDay.WEEKEND)
                .state(ChildDay.WEEKDAY)
                .end(ChildDay.END);

        /*builder.configureTransitions()
                .withExternal()
                .source(source)
                .target(target)
                .event(event);*/

        return builder.build();
    }
    public DayStateMachineBuilder build(String source, String target, String event, Action<ChildDay, ChildEvent> action) throws Exception {
        StateMachineBuilder.Builder<ChildDay, ChildEvent> builder = StateMachineBuilder.builder();

        if (action != null) {
            builder.configureTransitions()
                    .withExternal()
                    .source(ChildDay.valueOf(source))
                    .target(ChildDay.valueOf(target))
                    .event(ChildEvent.valueOf(event))
                    .action(action);
        } else {
            builder.configureTransitions()
                    .withExternal()
                    .source(source)
                    .target(target);
        }


        return this;
    }

    public StateMachineListener<ChildDay, ChildEvent> listener() {
        return new StateMachineListenerAdapter<ChildDay, ChildEvent>() {
            @Override
            public void stateChanged(State<ChildDay, ChildEvent> from, State<ChildDay, ChildEvent> to) {
                logger.info("State change from " + from.getId() + "to " + to.getId());
            }
        };
    }

}
