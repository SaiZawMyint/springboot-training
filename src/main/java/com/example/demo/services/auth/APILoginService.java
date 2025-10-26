package com.example.demo.services.auth;

import com.example.demo.api.requests.LoginRequest;
import com.example.demo.api.response.LoginResponse;
import com.example.demo.dtos.user.UserDto;

public interface APILoginService {
	LoginResponse login(LoginRequest request) throws Exception;
	UserDto checkToken(String token) throws Exception;
}
