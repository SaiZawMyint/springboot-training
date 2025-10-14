package com.example.demo.services.product;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.product.ProductDTO;
import com.example.demo.persistence.model.product.Product;
import com.example.demo.persistence.model.product_category.ProductCategory;
import com.example.demo.persistence.repositories.product.ProductRepository;
import com.example.demo.persistence.repositories.product_category.ProductCategoryRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;

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
		
		if(productDTO.getProductCategoryId() != null) {
			ProductCategory productCategory = this.productCategoryRepository.findById(productDTO.getProductCategoryId())
					.orElse(null);
			
			product.setProductCategory(productCategory);
		}

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

	    return new ProductDTO(product);
	}

	@Override
	public void deleteProduct(long id) {
		productRepository.deleteById(id);

	}

	@Override
	public boolean isNameAlreadyExit(String name, Long ignoreId) {
		return (this.productRepository.getNameByIgnoreId(name, ignoreId) != null);
	}

}