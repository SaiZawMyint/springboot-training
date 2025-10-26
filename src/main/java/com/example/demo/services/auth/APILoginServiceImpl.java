package com.example.demo.services.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.api.requests.LoginRequest;
import com.example.demo.api.response.LoginResponse;
import com.example.demo.dtos.user.UserDto;
import com.example.demo.persistence.model.user.User;
import com.example.demo.persistence.repositories.user.UserRepository;

@Service
public class APILoginServiceImpl implements APILoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Value("${app.jwt.expiration-ms}")
    private long jwtExpirationMs;

	@Override
	public LoginResponse login(LoginRequest request) throws Exception {
		User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new Exception("User not found!"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new Exception("Password do not match!");
		}

		// success

		Map<String, Object> claims = new HashMap<>();
		List<String> roles = new ArrayList<>();
		roles.add(user.getRole().getCode());
		claims.put("roles", roles);
		String token = jwtService.generateToken(claims, user.getEmail());

		LoginResponse response = new LoginResponse();
		response.setToken(token);
		response.setUser(new UserDto(user));
		response.setExpiration(jwtExpirationMs);
		return response;
	}

	@Override
	public UserDto checkToken(String token) throws Exception {
		String email = jwtService.extractUsername(token);
		if(email == null || email.isEmpty()) {
			throw new Exception("Invalid token!");
		}
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new Exception("User not found!"));

		return new UserDto(user);
	}

}
