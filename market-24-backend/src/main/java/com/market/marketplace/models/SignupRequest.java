package com.market.marketplace.models;

import java.util.Set;

public class SignupRequest {

    private String userName;
    private Set<String> roles;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;

    public SignupRequest(String userName, Set<String> roles, String password, String name, String surname, String email, String phone) {
        this.userName = userName;
        this.roles = roles;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public SignupRequest() {
    }



    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
