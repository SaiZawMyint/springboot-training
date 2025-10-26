package com.example.demo.common;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.demo.persistence.model.user.User;
public class AuthUser implements  org.springframework.security.core.userdetails.UserDetails{
	private static final long serialVersionUID = 2641187993359789263L;
	private final User user;
	public AuthUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(user.getRole().getCode()));
	} //to ask

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

}
