package com.example.demo.dtos.product_category;

import com.example.demo.dtos.BaseDTO;
import com.example.demo.persistence.model.product_category.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDTO extends BaseDTO{
	private String name;
	private String code;
	private Integer sequence;
	private Integer status;
	
	public ProductCategoryDTO(ProductCategory pc) {
		this.name = pc.getName();
		this.code = pc.getCode();
		this.sequence = pc.getSequence();
		this.status = pc.getStatus();
		
		// call common method
		setCommonField(pc);
	}
}
