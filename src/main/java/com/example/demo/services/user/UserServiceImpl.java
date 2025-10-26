package com.example.demo.services.user;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.user.UserDto;
import com.example.demo.persistence.model.role.Role;
import com.example.demo.persistence.model.user.User;
import com.example.demo.persistence.repositories.role.RoleRepository;
import com.example.demo.persistence.repositories.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto saveUser(UserDto userDTO) {
		User user;

		if (userDTO.getId() != null) {
			// Updating existing user
			user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new RuntimeException("User not found"));

			 if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
		            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		        }
			 System.out.println("existing user"+ user.getPassword());
		} else {
			// New user
			user = new User();

			 if (userDTO.getPassword() == null || userDTO.getPassword().isBlank()) {
		            throw new IllegalArgumentException("Password is required for new user");
		        }
		        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

		        System.out.println("new user"+ user.getPassword());

		}

		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());


		Role role = roleRepository.findById(userDTO.getRoleId()).orElse(null);

		user.setRole(role);
		User savedUser = this.userRepository.save(user);
		return new UserDto(savedUser);
	}

	@Override
	public List<UserDto> getAllUserList() {
		List<User> dataList = this.userRepository.findAll();
		if (dataList != null && !dataList.isEmpty()) {
			return dataList.stream().map(UserDto::new).toList();
		}

		return Collections.emptyList();
	}

	@Override
	public UserDto getById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found for id : " + id));

		return new UserDto(user);
	}

	@Override
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public boolean isNameAlreadyExit(String username, Long ignoreId) {
		return (this.userRepository.getNameByIgnoreId(username, ignoreId) != null);
	}

}
