package com.example.demo.services.product_category;

import java.util.List;

import com.example.demo.dtos.product_category.ProductCategoryDTO;

public interface ProductCategoryService {
	ProductCategoryDTO saveProductCategory(ProductCategoryDTO dto);
	List<ProductCategoryDTO> getAllProductCategories();
	ProductCategoryDTO updateProductCategory(ProductCategoryDTO dto);
	void deleteProductCategory(Long id);
}
