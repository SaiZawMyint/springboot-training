package com.example.demo.dtos.product;

import java.math.BigDecimal;

import com.example.demo.persistence.model.product.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private Long id;
	private String name;
	private String description;
	private Integer status; // 1,2
	private String statusDesc; // 1 = Active, 2= inactive
	private BigDecimal prices;

	public ProductDTO(Product product) {
		if(product != null) {
			this.id = product.getId();
			this.name = product.getName();
			this.description = product.getDescription();
			this.status = product.getStatus();
			this.prices = product.getPrice();
			
			this.statusDesc = status != null && status.equals(1) ? "Active":"Inactive";
			
		}
	}
}
