package com.example.easyshop.service;

import com.example.easyshop.entity.Role;
import com.example.easyshop.entity.Token;

import java.util.List;

public class UserData {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private List<Role> roles;
    private List<Token> tokens;

    public UserData() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
