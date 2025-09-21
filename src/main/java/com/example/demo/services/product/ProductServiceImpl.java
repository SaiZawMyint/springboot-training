package com.example.demo.services.product;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.product.ProductDTO;
import com.example.demo.persistence.model.product.Product;
import com.example.demo.persistence.model.product.ProductCategory;
import com.example.demo.persistence.model.user.User;
import com.example.demo.persistence.repositories.product.ProductRepository;
import com.example.demo.persistence.repositories.productcategory.ProductCategoryRepository;
import com.example.demo.persistence.repositories.user.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	 @PersistenceContext
	 private EntityManager entityManager;

	@Override
	public ProductDTO saveProduct(ProductDTO productDTO) { 
		Product product;
    if (productDTO.getId() != null) {
        // Update
        product = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
    } else {
        product = new Product();
        product.setCreatedAt(new Date());
    }

    product.setName(productDTO.getName());
    product.setDescription(productDTO.getDescription());
    product.setPrice(productDTO.getPrice());
    product.setStatus(productDTO.getStatus());
    product.setUpdatedAt(new Date());

    if (productDTO.getCategoryId() != null && productDTO.getCategoryId() > 0) {
        ProductCategory category = (ProductCategory) productCategoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
    }


    // Set creator (user)
    if (productDTO.getCreatedById() != null) {
        User user = userRepository.findById(productDTO.getCreatedById())
                .orElseThrow(() -> new RuntimeException("User not found"));
        product.setCreatedBy(user);
    }

    Product saved = productRepository.save(product);
    return new ProductDTO(saved);}

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

}