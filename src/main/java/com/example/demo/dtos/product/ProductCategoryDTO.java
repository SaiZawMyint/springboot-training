package com.example.demo.dtos.product;

import java.util.Date;

import com.example.demo.persistence.model.product.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDTO {

    private Long id;
    private String name;
    private String code;
    private String imageUrl;
    private Long createdById; // ID of the user who created
    private Date createdAt;
    private Date updatedAt;

    public ProductCategoryDTO(ProductCategory category) {
        if(category != null) {
            this.id = category.getId();
            this.name = category.getName();
            this.code = category.getCode();
            this.imageUrl = category.getImageUrl();
            this.createdById = category.getCreatedBy() != null ? category.getCreatedBy().getId() : null;
            this.createdAt = category.getCreatedAt();
            this.updatedAt = category.getUpdatedAt();
        }
    }
}
