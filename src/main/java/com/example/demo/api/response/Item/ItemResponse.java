package com.example.demo.api.response.Item;

import java.math.BigDecimal;

import com.example.demo.dtos.item.ItemDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemResponse {
	private Long id;
	private String name;
	
	@JsonProperty("sell_price")
	private BigDecimal sellPrice;

	@JsonProperty("original_price")
	private BigDecimal originalPrice;

	private Integer quantity; // e.g., 1, 2

	private Integer status; // 1 = Active, 2 = Inactive
	
	@JsonProperty("status_desc")
	private String statusDesc;
	
	public ItemResponse copyFormDTO(ItemDTO dto) {
		id = dto.getId();
		name = dto.getName();
		sellPrice = dto.getSellPrice();
		originalPrice = dto.getOriginalPrice();
		quantity=dto.getQuantity();
		status = dto.getStatus();
		statusDesc = dto.getStatusDesc();
	
		return this;
	}
}
