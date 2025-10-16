package com.example.demo.services.user;

import java.util.List;

import com.example.demo.dtos.user.UserDto;

public interface UserService {

	//save
	UserDto saveUser(UserDto userDTO);

	List<UserDto> getAllUserList();

	// update
	UserDto getById(Long id);

	// delete
	void deleteUser(long id);
}
