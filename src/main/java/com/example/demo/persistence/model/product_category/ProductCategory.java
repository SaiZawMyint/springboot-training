package com.example.demo.persistence.model.product_category;

import com.example.demo.persistence.model.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "product_category")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCategory extends BaseEntity{
	private String name;
	private String code;
	private Integer sequence;
	private Integer status;
}
