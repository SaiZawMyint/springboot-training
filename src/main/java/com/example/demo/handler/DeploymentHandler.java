package com.example.demo.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.persistence.model.role.Role;
import com.example.demo.persistence.model.user.User;
import com.example.demo.persistence.repositories.role.RoleRepository;
import com.example.demo.persistence.repositories.user.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DeploymentHandler {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void preDeploy() {
		List<Role> roles = roleRepository.findAll();
		List<User> users = userRepository.findAll();
		if((roles == null || roles.isEmpty()) && (users == null || users.isEmpty())) {
			// set new row 
			Role adminRole = new Role();
			adminRole.setName("Admin");
			adminRole.setCode("ROLE_ADMIN");
			
			Role admin = roleRepository.save(adminRole);
			
			User user = new User();
			user.setUsername("Admin");
			user.setEmail("admin@gmail.com");
			user.setPassword(passwordEncoder.encode("123456"));
			user.setRole(admin);
			userRepository.save(user);
		}
	}
	
}
