package com.example.familyproj.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SendMessengerService {

    private final JmsTemplate jmsTemplate;


    public void sendMsg(String msg){
        jmsTemplate.convertAndSend("ChildQueue",msg);
    }

}