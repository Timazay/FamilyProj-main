package com.example.familyproj.test.controller;

import com.example.familyproj.test.entity.Child;
import com.example.familyproj.test.entity.state.ChildDay;
import com.example.familyproj.test.entity.state.ChildEvent;

import com.example.familyproj.test.service.ChildService;
import com.example.familyproj.test.service.SendMessengerService;
import com.example.familyproj.test.service.TypeSafeSMService;
import com.example.familyproj.test.util.ConfigProvider;
import com.typesafe.config.Config;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class SendTestController {
    /* @Autowired
     private ChildDayService childDayService;*/
    @Autowired
    private ChildService childService;
    @Autowired
    private TypeSafeSMService typeSafeSMService;
    @Autowired
    private SendMessengerService service;

    @GetMapping("/school")
    public String sendEventGoToSchool(@RequestParam Long id) throws Exception {
  /*      Child anton = new Child();
        anton.setName("Anton");
        anton.setSurname("Mas");
        anton.setAge(11);
        childService.create(anton);*/
        Child child = childService.findById(id);
        Config config = ConfigProvider.config;
        StateMachine<ChildDay, ChildEvent> sm = typeSafeSMService.receiveSM(config);
        sm.sendEvent(ChildEvent.GOING_TO_SCHOOL);
        sm.start();
        return "Message sent with ID: " + id;
    }

    @GetMapping("/send")
    public String sendMsg(@RequestParam String msg) {
        service.sendMsg(msg);
        return "success";
    }

}
