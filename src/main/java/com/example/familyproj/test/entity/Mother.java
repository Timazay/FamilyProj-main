package com.example.familyproj.test.entity;


import com.example.familyproj.test.entity.state.ChildDay;
import com.example.familyproj.test.entity.state.ChildEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mother implements Member{

  public String name;

  public int age;


  public void setChildEvent(Child child, ChildDay day) {
    child.setDay(day);
  }
}