package com.mitutor.dtos.Responses;

public class UserRegisterResponse {

    private Integer id;
    private String username;
    private String email;
    private String role;

    public UserRegisterResponse() {
    }

    public UserRegisterResponse withId(Integer id) {
        this.setId(id);
        return this;
    }

    public UserRegisterResponse withUsername(String username) {
        this.setUsername(username);
        return this;
    }

    public UserRegisterResponse withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    public UserRegisterResponse withRole(String role) {
        this.setRole(role);
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
