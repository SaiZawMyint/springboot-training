package com.example.demo.persistence.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.persistence.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
//	Optional<Product> findByName(String name);
	
	@Query(value = "SELECT p FROM Product p WHERE p.name = :name AND (p.id != :ignoreId OR :ignoreId IS NULL)")
	Product getNameByIgnoreId(@Param(value = "name") String name, @Param(value = "ignoreId") Long ignoreId);
}