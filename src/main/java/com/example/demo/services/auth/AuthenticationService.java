package com.example.demo.services.auth;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.common.AuthUser;
import com.example.demo.persistence.model.role.Role;
import com.example.demo.persistence.model.user.User;
import com.example.demo.persistence.repositories.user.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		Role role = user.getRole_id();

		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(role.getCode()));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);

	}

	public User getCurrentUser() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || !authentication.isAuthenticated()) {
	        return null;
	    }

	    Object principal = authentication.getPrincipal();

	    if (principal instanceof AuthUser userDetails) {
	        return userDetails.getUser();
	    }

	    if (principal instanceof String email) {
	        return userRepository.findByEmail(email).orElse(null);
	    }

	    return null;
	}

}
