package com.example.demo.dtos.product;

import com.example.demo.dtos.BaseDTO;
import com.example.demo.persistence.model.product.ProductCategory;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDTO extends BaseDTO{


    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @NotBlank(message = "Code cannot be empty!")
    private String code;
    private String imageUrl;


    public ProductCategoryDTO(ProductCategory category) {
        if(category != null) {
            this.name = category.getName();
            this.code = category.getCode();
            this.imageUrl = category.getImageUrl();
            setCommonField(category);
        }
    }
}
