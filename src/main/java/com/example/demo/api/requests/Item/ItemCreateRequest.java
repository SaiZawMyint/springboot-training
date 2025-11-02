package com.example.demo.api.requests.Item;

import java.math.BigDecimal;

import com.example.demo.dtos.item.ItemDTO;
import com.example.demo.dtos.product.ProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemCreateRequest {
	@NotBlank(message = "Name cannot be empty!")
	private String name;
	
	@NotNull(message = "Sell Price cannot be empty!")
	@JsonProperty("sell_price")
	private BigDecimal sellPrice;

	@NotNull(message = "Original Price cannot be empty!")
	@JsonProperty("original_price")
	private BigDecimal originalPrice;

	@NotNull
	private Integer quantity; // e.g., 1, 2

	private Integer status; // 1 = Active, 2 = Inactive
	
	@JsonProperty("status_desc")
	private String statusDesc;

	private ProductDTO productDTO;
	private Long product;
	
	public ItemDTO convertToDTO() {
		ItemDTO dto = new ItemDTO();
		dto.setName(name);
		dto.setSellPrice(sellPrice);
		dto.setOriginalPrice(originalPrice);
		dto.setQuantity(quantity);
		dto.setStatus(status);
		dto.setStatusDesc(statusDesc);
		dto.setProductDTO(productDTO);
		dto.setProduct(product);
		return dto;
	}
}
