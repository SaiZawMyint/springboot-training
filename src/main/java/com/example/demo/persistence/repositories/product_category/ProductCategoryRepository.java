package com.example.demo.persistence.repositories.product_category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.model.product_category.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{

}
