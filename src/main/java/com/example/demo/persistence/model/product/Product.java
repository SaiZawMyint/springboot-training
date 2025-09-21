package com.example.demo.persistence.model.product;

import java.math.BigDecimal;

import com.example.demo.persistence.model.BaseEntity;
import com.example.demo.persistence.model.product_category.ProductCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "product")
@Data
@EqualsAndHashCode(callSuper =  true)
public class Product extends BaseEntity{
	
	private String name;
	private String description;
	private Integer status;
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "product_category_id")
	private ProductCategory productCategory;
}
