package com.example.demo.dtos.user;

import com.example.demo.dtos.BaseDTO;
import com.example.demo.dtos.role.RoleDto;
import com.example.demo.persistence.model.user.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDTO{
	//private Long id;
	@NotBlank(message = "Username is required")
	private String username;

	@NotBlank(message = "Email is required")
	private String email;

	//@NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be 8-20 characters")
	private String password;

	private RoleDto role;
	private Long roleId;
	public UserDto(User user) {
		if(user != null) {
		//	this.id = user.getId();
			this.username = user.getUsername();
			this.email = user.getEmail();
			this.password = user.getPassword();
			//this.role_id = user.getRole_id();
			this.role = new RoleDto(user.getRole());
			this.roleId = role.getId();


			setCommonField(user);
		}

	}
}
