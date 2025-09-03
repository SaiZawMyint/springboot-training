package com.example.demo.services.product;

import java.util.List;

import com.example.demo.dtos.product.ProductDTO;

public interface ProductService {
	// save
	ProductDTO saveProduct(ProductDTO productDTO);
	
	// list
	List<ProductDTO> getAllProductList();
	
	
	// update
	
	// delete
}
