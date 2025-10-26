package com.example.demo.dtos.product;

import java.math.BigDecimal;

import com.example.demo.dtos.BaseDTO;
import com.example.demo.persistence.model.product.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends BaseDTO {

	@NotBlank(message = "Name cannot be empty!")
    private String name;

	@NotBlank(message = "Description cannot be empty!")
    private String description;

    private Integer status;
	//@NotBlank(message = "staus cannot be empty!")
    private String statusDesc; // Active / Inactive

	@NotNull
   // @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    private BigDecimal price;

	private ProductCategoryDTO productCategoryDTO;

	//@NotNull(message = "Please select a product category!")
	private Long productCategoryId;

	public ProductDTO(Product product) {
	    if (product != null) {
	        this.name = product.getName();
	        this.description = product.getDescription();
	        this.status = product.getStatus();
	        this.price = product.getPrice();
	        this.statusDesc = status != null && status.equals(1) ? "Active" : "Inactive";

	        // Safe category mapping
	        if (product.getCategory() != null) {
                this.productCategoryDTO = new ProductCategoryDTO(product.getCategory());
                this.productCategoryId = product.getCategory().getId();
            }
	        // Common fields
	        setCommonField(product);
	    }
	}

}
