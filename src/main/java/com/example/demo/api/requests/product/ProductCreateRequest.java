package com.example.demo.api.requests.product;

import java.math.BigDecimal;

import com.example.demo.dtos.product.ProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductCreateRequest {
	@NotBlank(message = "Name cannot be empty!")
	private String name;
	@NotBlank(message = "Description cannot be empty!")
	private String description;
	
	@Min(value = 0, message = "Minimum value is 0.")
	private BigDecimal price;
	@Min(value = 0, message = "Minimum value is 0.")
	private Integer status;
	@JsonProperty("product_category_id")
	@NotNull
	private Long productCategoryId;
	
	public ProductDTO convertToDTO() {
		ProductDTO dto = new ProductDTO();
		dto.setName(name);
		dto.setDescription(description);
		dto.setStatus(status);
		dto.setPrices(price);
		dto.setProductCategoryId(productCategoryId);
		
		return dto;
	}
}
