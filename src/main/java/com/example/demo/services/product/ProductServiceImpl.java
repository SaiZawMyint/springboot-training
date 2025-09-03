package com.example.demo.services.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.product.ProductDTO;
import com.example.demo.persistence.model.product.Product;
import com.example.demo.persistence.repositories.product.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDTO saveProduct(ProductDTO productDTO) {
		
		Product product = new Product();
		
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrices());
		product.setStatus(productDTO.getStatus());
		
		Product saved = this.productRepository.save(product);
		
		return new ProductDTO(saved);
	}

	@Override
	public List<ProductDTO> getAllProductList() {
		
		List<Product> dataList = this.productRepository.findAll();
		
		if(dataList != null && !dataList.isEmpty()) {
			return dataList.stream().map(ProductDTO::new).toList();
		}
		
		return Collections.emptyList();
	}

}