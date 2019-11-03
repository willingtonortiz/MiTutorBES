package com.mitutor.dtos.output;

import java.io.Serializable;

public class AuthenticatedUserOutput implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String email;
    private String role;
    private String token;

    public AuthenticatedUserOutput() {
    }

    public AuthenticatedUserOutput withId(Integer id) {
        this.setId(id);
        return this;
    }

    public AuthenticatedUserOutput withUsername(String username) {
        this.setUsername(username);
        return this;
    }

    public AuthenticatedUserOutput withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    public AuthenticatedUserOutput withRole(String role) {
        this.setRole(role);
        return this;
    }

    public AuthenticatedUserOutput withToken(String token) {
        this.setToken(token);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
