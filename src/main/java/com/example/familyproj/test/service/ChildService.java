package com.example.familyproj.test.service;

import com.example.familyproj.test.entity.Child;
import com.example.familyproj.test.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChildService {
    public static final String CHILD_ID_HEADER = "child_id";
    private final ChildRepository repository;

    @Autowired
    public ChildService(ChildRepository repository) {
        this.repository = repository;
    }

public Child findById(Long id){
    return repository.getOne(id);
}
    public void create(Child child) {
        repository.save(child);
    }
}
