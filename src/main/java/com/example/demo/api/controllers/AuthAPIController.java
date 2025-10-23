package com.example.demo.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.requests.LoginRequest;
import com.example.demo.api.response.BaseResponse;
import com.example.demo.api.response.LoginResponse;
import com.example.demo.dtos.user.UserDTO;
import com.example.demo.services.auth.APILoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthAPIController {
	
	@Autowired
	private APILoginService apiLoginService;

	@GetMapping("/test")
	public String test() {
		return "Success";
	}
	
	@PostMapping("/login")
	public ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody@Valid LoginRequest request, BindingResult result) {
		BaseResponse<LoginResponse> response = new BaseResponse<>();
		
		if(result.hasErrors()) {
			response.setSuccess(false);
			response.setStatusCode(-1);
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			response.setSuccess(true);
			response.setStatusCode(1);
			response.setData(this.apiLoginService.login(request));
		}catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/token")
	public ResponseEntity<BaseResponse<UserDTO>> checkToken(@RequestParam(required = false, value = "token") String token){
		
		BaseResponse<UserDTO> response = new BaseResponse<UserDTO>();
		
		try {
			response.setStatusCode(1);
			response.setSuccess(true);
			response.setData(apiLoginService.checkToken(token));
			return ResponseEntity.ok().body(response);
		}catch (Exception e) {
			response.setStatusCode(-1);
			response.setSuccess(false);
			return ResponseEntity.internalServerError().body(response);
		}
	}
}
