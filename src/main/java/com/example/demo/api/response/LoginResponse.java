package com.example.demo.api.response;

import com.example.demo.dtos.user.UserDTO;

import lombok.Data;

@Data
public class LoginResponse {

	private UserDTO user;
	private String token;
	private Long expiration;
	
}
