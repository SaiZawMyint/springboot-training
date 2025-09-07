package com.example.demo.persistence.repositories.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.model.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
