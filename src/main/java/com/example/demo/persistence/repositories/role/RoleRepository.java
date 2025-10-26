package com.example.demo.persistence.repositories.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.persistence.model.role.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query(value = "SELECT p FROM Role p WHERE p.name = :name AND (p.id != :ignoreId OR :ignoreId IS NULL)")
	Role getNameByIgnoreId(@Param(value = "name") String name, @Param(value = "ignoreId") Long ignoreId);
}
