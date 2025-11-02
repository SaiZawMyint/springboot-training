package com.example.demo.api.requests.Role;

import com.example.demo.dtos.role.RoleDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleCreateRequest {

	@NotBlank(message = "Name cannot be empty!")
	private String name;
	@NotBlank(message = "Code cannot be empty!")
	private String code;
	public RoleDto convertToDto() {
		RoleDto dto =new RoleDto();
		dto.setName(name);
		dto.setCode(code);
		return dto;
		
	}
}
