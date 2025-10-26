package com.example.demo.persistence.repositories.productcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.persistence.model.product.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
	@Query(value = "SELECT p FROM ProductCategory p WHERE p.name = :name AND (p.id != :ignoreId OR :ignoreId IS NULL)")
	ProductCategory getNameByIgnoreId(@Param(value = "name") String name, @Param(value = "ignoreId") Long ignoreId);
}