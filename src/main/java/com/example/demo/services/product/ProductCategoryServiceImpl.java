package com.example.demo.services.product;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.product.ProductCategoryDTO;
import com.example.demo.persistence.model.product.ProductCategory;
import com.example.demo.persistence.model.user.User;
import com.example.demo.persistence.repositories.productcategory.ProductCategoryRepository;
import com.example.demo.persistence.repositories.user.UserRepository;
import com.example.demo.services.auth.AuthenticationService;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    //save
	@Override
	public ProductCategoryDTO saveProductCategory(ProductCategoryDTO productCategoryDTO) {
		ProductCategory pdtCategory;

		if (productCategoryDTO.getId() != null) {
			// Update
			pdtCategory = productCategoryRepository.findById(productCategoryDTO.getId())
					.orElseThrow(() -> new RuntimeException("Product Category not found"));
		} else {
			pdtCategory = new ProductCategory();
			pdtCategory.setCreatedAt(new Date());
		}

		pdtCategory.setName(productCategoryDTO.getName());
		pdtCategory.setCode(productCategoryDTO.getCode());

		pdtCategory.setImageUrl(productCategoryDTO.getImageUrl());


		  User currentUser = authenticationService.getCurrentUser();
		  pdtCategory.setCreatedBy(currentUser);

		ProductCategory saved = productCategoryRepository.save(pdtCategory);
		return new ProductCategoryDTO(saved);
	}

	@Override
	public List<ProductCategoryDTO> getAllProductCategoryList() {
		/*List<ProductCategory> dataList = this.productCategoryRepository.findAll();

		if (dataList != null && !dataList.isEmpty()) {
			return dataList.stream().map(ProductCategoryDTO::new).toList();
		}

		return Collections.emptyList();*/

		  return productCategoryRepository.findAll()
	                .stream()
	                .map(ProductCategoryDTO::new)
	                .toList();
	}

	@Override
	public ProductCategoryDTO getById(Long id) {
		ProductCategory pdtCategory = productCategoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found for id : " + id));
		User currentUser = authenticationService.getCurrentUser();
		pdtCategory.setUpdatedBy(currentUser);
		return new ProductCategoryDTO(pdtCategory);
	}


	@Override
	public void deleteProductCategory(long id) {
		if (!productCategoryRepository.existsById(id)) {
            throw new RuntimeException("Product category not found with id: " + id);
        }
		productCategoryRepository.deleteById(id);

	}

	@Override
	public boolean isNameAlreadyExit(String name, Long ignoreId) {
		return (this.productCategoryRepository.getNameByIgnoreId(name, ignoreId) != null);
	}
}

