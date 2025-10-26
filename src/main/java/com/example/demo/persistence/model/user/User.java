package com.example.demo.persistence.model.user;

import com.example.demo.persistence.model.BaseEntity;
import com.example.demo.persistence.model.role.Role;

import groovy.transform.EqualsAndHashCode;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper =  true)
public class User extends BaseEntity{

	private String username;
	private String email;
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
}