package com.example.demo.dtos.user;

import com.example.demo.dtos.BaseDTO;
import com.example.demo.dtos.roles.RoleDTO;
import com.example.demo.persistence.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO{

	private String username;
	private String email;
	private String password;
	private RoleDTO role;
	private Long roleId; // this is for view
	
	public UserDTO(User user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		// password do not need to set for security reason
		if(user.getRole() != null) {
			this.role = new RoleDTO(user.getRole());
			this.roleId = role.getId();
		}
		
		// call the common method to auto bind common data
		setCommonField(user);
	}
	
}
