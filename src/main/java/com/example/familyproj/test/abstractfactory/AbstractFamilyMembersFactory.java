package com.example.familyproj.test.abstractfactory;


import com.example.familyproj.test.entity.Child;
import com.example.familyproj.test.entity.Father;
import com.example.familyproj.test.entity.Mother;

public class AbstractFamilyMembersFactory implements FamilyMembersFactory {

    public Mother createMother() {
        return new Mother();
    }

    public Father createFather() {
        return new Father();
    }

    public Child createChild() {
        return new Child();
    }

}