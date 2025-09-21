package com.example.demo.services.role;

import java.util.List;
import com.example.demo.dtos.role.RoleDto;

public interface RoleService {
	
	//save
	RoleDto saveRole(RoleDto roleDTO);

	List<RoleDto> getAllRoleList();
	
	// update
	RoleDto getById(Long id);

	// delete
	void deleteRole(long id);

}
