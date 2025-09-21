package com.example.demo.dtos.user;

import com.example.demo.persistence.model.role.Role;
import com.example.demo.persistence.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
	private Long id;
	private String username;
	private String email;
	private String password;
	private Role role_id;
	public UserDto(User user) {
		if(user != null) {
			this.id = user.getId();
			this.username = user.getUsername();
			this.email = user.getEmail();
			this.password = user.getPassword();
			this.role_id = user.getRole_id();
		}

	}
}
