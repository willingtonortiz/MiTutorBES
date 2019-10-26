package com.mitutor.dtos.Responses;

public class UserRegisterResponse {

	private String username;
	private Integer id;
	private String password;
	private String email;
	private String role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username= name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
