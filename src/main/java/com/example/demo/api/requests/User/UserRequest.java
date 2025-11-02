package com.example.demo.api.requests.User;

import com.example.demo.dtos.user.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
	//private Long id;
		@NotBlank(message = "Username is required")
		private String username;

		@NotBlank(message = "Email is required")
		private String email;

		//@NotBlank(message = "Password is required")
	    @Size(min = 8, max = 20, message = "Password must be 8-20 characters")
		private String password;

		//private RoleDto role;
			
		@JsonProperty("role_id")
		@NotNull
		private Long roleId;
		
		public UserDto convertToDTO() {
			UserDto dto = new UserDto();
			dto.setUsername(username);
			dto.setEmail(email);
			dto.setPassword(password);
			dto.setRoleId(roleId);
			return dto;
		}
}
