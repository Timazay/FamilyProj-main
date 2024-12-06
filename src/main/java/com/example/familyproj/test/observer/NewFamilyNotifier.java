package com.example.familyproj.test.observer;


import com.example.familyproj.test.entity.Family;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Set;


public class NewFamilyNotifier {
    @Autowired
    private Set<Family> familyNewsSubs;

    public void notifyNewFamily() {

    }

    public void addToNotifier(Family family) {
        familyNewsSubs.add(family);
    }

}