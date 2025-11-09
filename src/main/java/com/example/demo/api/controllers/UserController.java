package com.example.demo.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.requests.User.UserRequest;
import com.example.demo.api.response.BaseResponse;
import com.example.demo.api.response.User.UserResponse;
import com.example.demo.dtos.user.UserDto;
import com.example.demo.services.user.UserService;

@RestController("apiUserController")
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public ResponseEntity<BaseResponse<?>> getUserList() {
	    BaseResponse<List<UserResponse>> response = new BaseResponse<>();

	    try {
	        response.setSuccess(true);
	        response.setStatusCode(1);
	        response.setData(
	            userService.getAllUserList()
	                .stream()
	                .map(t -> new UserResponse().copyFormDTO(t))
	                .toList()
	        );

	        return ResponseEntity.ok().body(response);
	    } catch (Exception e) {
	        response.setSuccess(false);
	        response.setStatusCode(-1);
	        return ResponseEntity.internalServerError().body(response);
	    }
	}


	@PostMapping("/create")
	public ResponseEntity<BaseResponse<?>> createUser(@RequestBody UserRequest request) {
		BaseResponse<UserResponse> response = new BaseResponse<UserResponse>();
		try {
			UserDto saved = userService.saveUser(request.convertToDTO());

			response.setData(new UserResponse().copyFormDTO(saved));
			response.setStatusCode(1);
			response.setSuccess(true);
			 response.setMessage("Create User success!");
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			 response.setMessage(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	// update
	@PutMapping("/user/{id}")
	public ResponseEntity<BaseResponse<?>> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
		BaseResponse<UserResponse> response = new BaseResponse<UserResponse>();

		try {
			// 1. Fetch existing user
			UserDto existingUser = userService.getById(id);
			if (existingUser == null) {
				response.setStatusCode(-1);
				response.setSuccess(false);
				response.setMessage("User not found with ID: " + id);
				return ResponseEntity.internalServerError().body(response);
			}

			// 2. Update fields using the request
			existingUser.setUsername(request.getUsername());
			existingUser.setEmail(request.getEmail());

			// 3. Save updated user
			UserDto updtUser = userService.saveUser(existingUser);

			// 4. Set response
			response.setData(new UserResponse().copyFormDTO(updtUser));
			response.setStatusCode(1);
			response.setSuccess(true);
			response.setMessage("User updated successfully!");

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			response.setMessage("Error updating user: " + e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BaseResponse<?>> deleteUser(@PathVariable("id") Long pId){
		BaseResponse<Long> response = new BaseResponse<Long>();
		try {
			this.userService.deleteUser(pId);
			response.setStatusCode(1);
			response.setSuccess(true);
			response.setMessage("Delete User success!");
			response.setData(pId);
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
		return ResponseEntity.ok(response);
	}

}
