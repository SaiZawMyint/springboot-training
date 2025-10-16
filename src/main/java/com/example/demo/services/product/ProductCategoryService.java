package com.example.demo.services.product;

import java.util.List;

import com.example.demo.dtos.product.ProductCategoryDTO;

public interface ProductCategoryService {

	// save
		ProductCategoryDTO saveProductCategory(ProductCategoryDTO productCategoryDTO);

		// list
		List<ProductCategoryDTO> getAllProductCategoryList();

		// update
		ProductCategoryDTO getById(Long id);

		// delete
		void deleteProductCategory(long id);
}
