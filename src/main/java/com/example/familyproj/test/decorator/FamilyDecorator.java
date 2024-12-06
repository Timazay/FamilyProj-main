package com.example.familyproj.test.decorator;

import com.example.familyproj.test.entity.Family;
import lombok.Data;

@Data
public class FamilyDecorator extends Family {
    private int balance;

    public void showDetails(){

    }
}
