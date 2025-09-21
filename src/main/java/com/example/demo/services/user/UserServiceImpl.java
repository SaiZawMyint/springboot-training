package com.example.demo.services.user;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.user.UserDto;
import com.example.demo.persistence.model.user.User;
import com.example.demo.persistence.repositories.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto saveUser(UserDto userDTO) {
		User user;

		user = new User();
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());

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

}
