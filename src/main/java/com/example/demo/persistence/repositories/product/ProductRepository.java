package com.example.demo.persistence.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}