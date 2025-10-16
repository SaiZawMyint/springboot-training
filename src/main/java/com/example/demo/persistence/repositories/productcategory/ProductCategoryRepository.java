package com.example.demo.persistence.repositories.productcategory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.model.product.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}