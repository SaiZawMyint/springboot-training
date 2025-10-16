package com.example.demo.dtos.role;

import com.example.demo.persistence.model.role.Role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
	private Long id;
	@NotBlank(message = "Name cannot be empty!")
	private String name;
	@NotBlank(message = "Code cannot be empty!")
	//@Pattern(regexp = "")
	private String code;
	public RoleDto(Role role) {
		if(role != null) {
			this.id = role.getRole_id();
			this.name = role.getName();
			this.code = role.getCode();

		}

	}
}
