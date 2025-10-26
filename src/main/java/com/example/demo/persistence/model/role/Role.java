package com.example.demo.persistence.model.role;

import java.util.List;

import com.example.demo.persistence.model.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long role_id;
	private String name;
	private String code;

	@OneToMany(mappedBy = "role")
    private List<User> user;

}
