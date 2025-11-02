package com.example.demo.api.response.ProductCategory;

import com.example.demo.dtos.product.ProductCategoryDTO;

import lombok.Data;

@Data
public class ProductCategoryResponse {
	private Long id;
	private String name;
	private String code;

	private String imageUrl;
	public ProductCategoryResponse copyFormDTO(ProductCategoryDTO pdtCategoryDTO) {
		id =pdtCategoryDTO.getId();
		name =pdtCategoryDTO.getName();
		code =pdtCategoryDTO.getCode();
		imageUrl=pdtCategoryDTO.getImageUrl();
						
		return this;
		
	}
}
