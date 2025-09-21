package com.example.demo.dtos.roles;

import com.example.demo.dtos.BaseDTO;
import com.example.demo.persistence.model.role.Role;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO extends BaseDTO{
	private String name;
	private String code;
	
	public RoleDTO(Role role) {
		this.name = role.getName();
		this.code = role.getCode();
		
		// call common method
		setCommonField(role);
	}
}
