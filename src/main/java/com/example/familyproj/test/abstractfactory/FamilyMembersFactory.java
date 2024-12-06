package com.example.familyproj.test.abstractfactory;

import com.example.familyproj.test.entity.Child;
import com.example.familyproj.test.entity.Father;
import com.example.familyproj.test.entity.Mother;


public interface FamilyMembersFactory {

   Mother createMother();
   Father createFather();
   Child createChild();

}