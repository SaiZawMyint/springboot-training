package com.example.demo.api.response.product;

import java.math.BigDecimal;

import com.example.demo.dtos.product.ProductDTO;
import com.example.demo.utils.CommonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductResponse {
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Integer status;
	@JsonProperty("status_desc")
	private String statusDesc;
	@JsonProperty("price_desc")
	private String priceDesc;

	public ProductResponse copyFormDTO(ProductDTO dto) {
		id = dto.getId();
		name = dto.getName();
		description = dto.getDescription();
		price = dto.getPrices();
		status = dto.getStatus();
		statusDesc = dto.getStatusDesc();
		priceDesc = CommonUtils.formatNumber(dto.getPrices());
		return this;
	}

}
