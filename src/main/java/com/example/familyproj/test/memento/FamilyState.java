package com.example.familyproj.test.memento;


import com.example.familyproj.test.entity.Child;
import com.example.familyproj.test.entity.Family;

import com.example.familyproj.test.entity.Father;
import com.example.familyproj.test.entity.Mother;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FamilyState {
    private int id;
    private Mother mother;
    private Father father;
    private List<Child> children;
    {
        children= new ArrayList<>();
    }

    public void saveState(Family family) {
        this.mother = family.getMother();
        this.father = family.getFather();
        for (Child child: family.getChildren()) {
            this.children.add(child);
        }
    }

}