package com.example.demo.services.role;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.role.RoleDto;
import com.example.demo.persistence.model.role.Role;
import com.example.demo.persistence.repositories.role.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleDto saveRole(RoleDto roleDTO) {
		Role role;
		role = new Role();
		role.setRole_id(roleDTO.getId());
		role.setName(roleDTO.getName());
		role.setCode(roleDTO.getCode().toUpperCase()); //to add ADMIN_, to change uppder case

		Role savedRole = this.roleRepository.save(role);
		return new RoleDto(savedRole);
	}

	@Override
	public List<RoleDto> getAllRoleList() {
		List<Role> dataList = this.roleRepository.findAll();
		if (dataList != null && !dataList.isEmpty()) {
			return dataList.stream().map(RoleDto::new).toList();
		}

		return Collections.emptyList();
	}

	@Override
	public RoleDto getById(Long id) {
		Role role = roleRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Role not found for id : " + id));
		return new RoleDto(role);
	}

	@Override
	public void deleteRole(long id) {
		roleRepository.deleteById(id);
	}
}
