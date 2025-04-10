package com.example.familyproj.test.entity;


import com.example.familyproj.test.entity.state.ChildDay;
import com.example.familyproj.test.entity.state.ChildEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;

@Data
@NoArgsConstructor // java -jar FamilyProj-0.0.1-SNAPSHOT.jar --server.port=8081
@Entity
@Table(name = "children")
public class Child implements Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;

    private int age;
   @Enumerated(EnumType.STRING)
    public ChildDay day;

    public Attributes toAttributes() {
        Attributes attributes = new BasicAttributes();
        attributes.put("objectClass","inetOrgPerson");
        attributes.put("cn", name);
        attributes.put("sn", surname);
        return attributes;
    }
}