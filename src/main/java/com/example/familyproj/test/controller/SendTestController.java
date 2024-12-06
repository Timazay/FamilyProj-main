package com.example.familyproj.test.controller;

import com.example.familyproj.test.entity.Child;
import com.example.familyproj.test.entity.state.ChildDay;
import com.example.familyproj.test.entity.state.ChildEvent;
import com.example.familyproj.test.service.ChildDayService;
import com.example.familyproj.test.service.ChildService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class SendTestController {
    @Autowired
    private ChildDayService childDayService;
    @Autowired
    private ChildService childService;

    @GetMapping("/school")
    public String sendEventGoToSchool(@RequestParam Long id) {
        /*Child anton = new Child();
        anton.setName("Anton");
        anton.setSurname("Mas");
        anton.setAge(11);
        childService.create(anton);*/
        Child child = childService.findById(id);
        log.info(child.getDay().toString());
        childDayService.goToSchool(id);
        log.info(child.getDay().toString());
        return "Message sent with ID: " + id;
    }

    @GetMapping("/receive")
    public String receiveMsg() {
        //      childDayService.sendEvent(ChildEvent.SPORT_ACTIVITY);
        return "success";
    }


}
