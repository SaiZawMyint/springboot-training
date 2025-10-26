package com.example.demo.services.product;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.product.ProductDTO;
import com.example.demo.persistence.model.product.Product;
import com.example.demo.persistence.model.product.ProductCategory;
import com.example.demo.persistence.model.user.User;
import com.example.demo.persistence.repositories.product.ProductRepository;
import com.example.demo.persistence.repositories.productcategory.ProductCategoryRepository;
import com.example.demo.persistence.repositories.user.UserRepository;
import com.example.demo.services.auth.AuthenticationService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationService authenticationService;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ProductDTO saveProduct(ProductDTO productDTO) throws Exception {
		Product product;
		if (productDTO.getId() != null) {
			// Update
			product = productRepository.findById(productDTO.getId())
					.orElseThrow(() -> new RuntimeException("Product not found"));
		} else {
			product = new Product();

		}

		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setStatus(productDTO.getStatus());
		// product.setUpdatedAt(new Date());

		/*if (productDTO.getProductCategoryId() != null) {
			ProductCategory productCategory = productCategoryRepository.findById(productDTO.getProductCategoryId())
					.orElse(null);

			if (productCategory == null) {
				throw new RuntimeException("Invalid product category ID: " + productDTO.getProductCategoryId()
						+ ". Please select a valid category.");
			}

			product.setCategory(productCategory);
			// product.setProductCategory(productCategory);
		} else {
			throw new RuntimeException("Product category ID is required.");
		}*/
		ProductCategory productCategory = productCategoryRepository.findById(productDTO.getProductCategoryId())
				.orElse(null);
		product.setCategory(productCategory);
		System.out.println("Product category "+product.getCategory());

		User currentUser = authenticationService.getCurrentUser();
		product.setCreatedBy(currentUser);

		Product saved = this.productRepository.save(product);
		return new ProductDTO(saved);
	}

	@Override
	public List<ProductDTO> getAllProductList() {

		List<Product> dataList = this.productRepository.findAll();

		if (dataList != null && !dataList.isEmpty()) {
			return dataList.stream().map(ProductDTO::new).toList();
		}

		return Collections.emptyList();
	}

	@Override
	public ProductDTO getById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found for id : " + id));

		User currentUser = authenticationService.getCurrentUser();
		product.setUpdatedBy(currentUser);

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