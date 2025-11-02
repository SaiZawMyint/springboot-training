package com.example.demo.api.requests.ProductCategory;


import com.example.demo.dtos.product.ProductCategoryDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductCategoryCreateRequest {

	@NotBlank(message = "Name cannot be empty!")
	private String name;

	@NotBlank(message = "Code cannot be empty!")
	private String code;

	private String imageUrl;
	
	public ProductCategoryDTO convertToDTO() {
		ProductCategoryDTO pdtCategoryDTO=new ProductCategoryDTO ();
		pdtCategoryDTO.setName(name);
		pdtCategoryDTO.setCode(code);
		pdtCategoryDTO.setImageUrl(imageUrl);
		
		return pdtCategoryDTO;
	}

}
