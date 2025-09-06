package com.example.demo.services.product;

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
		Product product;
		 if (productDTO.getId() != null) {
	            // Updating existing product
	            product = productRepository.findById(productDTO.getId())
	                    .orElseThrow(() -> new RuntimeException("Product not found for update"));
	        } else {
	            // Creating new product
	            product = new Product();
	        }

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

	@Override
	public ProductDTO getById(Long id) {
	    Product product = productRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found for id : " + id));

	    // Convert entity -> DTO
	    ProductDTO dto = new ProductDTO();
	    dto.setId(product.getId());
	    dto.setDescription(product.getDescription());
	    dto.setPrices(product.getPrice());

	    return dto;
	}

	@Override
	public void deleteProduct(long id) {
		productRepository.deleteById(id);

	}

}