package com.example.demo.persistence.model.user;

import com.example.demo.persistence.model.BaseEntity;
import com.example.demo.persistence.model.role.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User extends BaseEntity{

	private String username;
	private String email;
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role_id;
}