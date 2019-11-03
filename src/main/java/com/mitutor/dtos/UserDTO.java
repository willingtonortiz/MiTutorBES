package com.mitutor.dtos;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String role;

    public UserDTO() {

    }

    public UserDTO withId(Integer id) {
        this.setId(id);
        return this;
    }

    public UserDTO withUsername(String username) {
        this.setUsername(username);
        return this;
    }

    public UserDTO withPassword(String password) {
        this.setPassword(password);
        return this;
    }

    public UserDTO withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    public UserDTO withRole(String role) {
        this.setRole(role);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
