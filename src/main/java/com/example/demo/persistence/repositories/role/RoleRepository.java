package com.example.demo.persistence.repositories.role;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.model.role.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
