package com.example.demo.persistence.repositories.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.persistence.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	@Query(value = "SELECT u FROM User u WHERE u.username = :username AND (u.id != :ignoreId OR :ignoreId IS NULL)")
	User getNameByIgnoreId(@Param(value = "username") String username, @Param(value = "ignoreId") Long ignoreId);
}
