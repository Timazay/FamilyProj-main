package com.example.familyproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;

@SpringBootApplication
public class FamilyProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyProjApplication.class, args);
    }

}
