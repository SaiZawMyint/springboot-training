package com.example.demo.services.product;

import java.util.List;

import com.example.demo.dtos.product.ProductDTO;

public interface ProductService {
	// save
	ProductDTO saveProduct(ProductDTO productDTO);

	// list
	List<ProductDTO> getAllProductList();

	// update
	ProductDTO getById(Long id);

	// delete
	void deleteProduct(long id) throws Exception;
	
	boolean isNameAlreadyExit(String name, Long ignoreId);
}
