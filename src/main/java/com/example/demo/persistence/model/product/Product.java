package com.example.demo.persistence.model.product;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.persistence.model.BaseEntity;
import com.example.demo.persistence.model.item.Item;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @JsonManagedReference
	@ManyToOne
    @JoinColumn(name = "category_id")
	private ProductCategory category;  // Many-to-One ProductCategory

    @OneToMany(mappedBy = "product")
    private List<Item> items;         // One-to-Many Items

}
