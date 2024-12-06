package com.example.familyproj.test.controller;

import com.example.familyproj.test.entity.Child;
import com.example.familyproj.test.service.ChildLdapService;
import com.example.familyproj.test.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
    @Autowired
    private ChildService childService;

    @Autowired
    private ChildLdapService childLdapService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/addUserForm")
    public String addUserForm(Model model) {
        Child child = new Child();
        child.setName("Artem");
        child.setSurname("Morey");
        childLdapService.addUser(child);
        model.addAttribute("Child", new Child());
        return "addUser";
    }

    /*@GetMapping("/export")
    public ResponseEntity<String> exportUsers() {
        Child child = new Child();
        child.setName("Anton");
        child.setSurname("Petrov");
        Child child1 = new Child();
        child1.setName("Dasha");
        child1.setSurname("Orlova");
        Child child2 = new Child();
        child2.setName("Roman");
        child2.setSurname("Kovalenko");

        childService.create(child);
        childService.create(child1);
        childService.create(child2);

        childService.exportChildToLdap();
        return ResponseEntity.ok("Users exported to LDAP successfully");
    }*/
}
