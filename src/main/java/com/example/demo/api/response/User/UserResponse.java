package com.example.demo.api.response.User;

import com.example.demo.dtos.user.UserDto;

import lombok.Data;

@Data
public class UserResponse {
	private Long id;
	private String username;
	private String email;
	private String password;
	
	public UserResponse copyFormDTO(UserDto dto) {
		id = dto.getId();
		username = dto.getUsername();
		email = dto.getEmail();
		password = dto.getPassword();
		return this;
	}

}
