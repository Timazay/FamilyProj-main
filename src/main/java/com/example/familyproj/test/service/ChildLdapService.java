package com.example.familyproj.test.service;

import com.example.familyproj.test.entity.Child;
import javax.naming.NamingException;
import javax.naming.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildLdapService {
    @Autowired
    private LdapTemplate ldapTemplate;
    private static final String BASE_DN = "cn=user-ro,dc=ramhlocal,dc=com";

    public void addUser(Child child) {
        ldapTemplate.bind("sn=" + child.getSurname() + "," + BASE_DN, null, child.toAttributes());

    }

    public List<Child> getAllUsers() {
        return ldapTemplate.search(BASE_DN, "(objectclass=inetOrgPerson)",
                (AttributesMapper<Child>) attributes ->{
                    Child child = new Child();
                    child.setName(attributes.get("cn").get().toString());
                    child.setSurname(attributes.get("sn").get().toString());
                    return child;
                });
    }

    public String getUserById(String sn) {
        List<String> usernames = ldapTemplate.search(
                BASE_DN,
                "(sn=" + sn + ")",
                (AttributesMapper<String>) attrs -> {
                    try {
                        return (String) attrs.get("sn").get();
                    } catch (NamingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        if (!usernames.isEmpty()) {
            return usernames.get(0);
        } else {
            return null; // User not found
        }
    }
    public void deleteUser(String sn) {
        Name userDn= LdapNameBuilder.newInstance(BASE_DN)
                .add("sn",sn)
                .build();
        ldapTemplate.unbind(userDn);
    }
}
