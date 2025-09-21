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
    private Integer status;
    private String statusDesc; // Active / Inactive
    private BigDecimal price;

    private Long categoryId;  // only ID
    private String categoryName; // optional, for display
    private Long createdById; 
    
    public ProductDTO(Product product) {
        if (product != null) {
            this.id = product.getId();
            this.name = product.getName();
            this.description = product.getDescription();
            this.status = product.getStatus();
            this.price = product.getPrice();
            this.statusDesc = status != null && status.equals(1) ? "Active" : "Inactive";

            this.categoryId = product.getCategory() != null ? product.getCategory().getId() : null;
            this.categoryName = product.getCategory() != null ? product.getCategory().getName() : null;
            this.createdById = product.getCreatedBy() != null ? product.getCreatedBy().getId() : null;
        }
	}
}
