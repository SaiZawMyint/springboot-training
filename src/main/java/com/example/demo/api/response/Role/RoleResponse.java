package com.example.demo.api.response.Role;

import com.example.demo.dtos.role.RoleDto;

import lombok.Data;

@Data
public class RoleResponse {
	private Long id;
	private String name;
	private String code;

	public RoleResponse copyFormDTO(RoleDto roleDto) {
		id = roleDto.getId();
		name = roleDto.getName();
		code = roleDto.getCode();

		return this;

	}
}
