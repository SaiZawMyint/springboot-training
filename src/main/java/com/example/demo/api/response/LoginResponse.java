package com.example.demo.api.response;

import com.example.demo.dtos.user.UserDto;

import lombok.Data;

@Data
public class LoginResponse {
	private UserDto user;
	private String token;
	private Long expiration;
}
