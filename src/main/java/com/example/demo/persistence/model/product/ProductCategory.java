package com.example.demo.persistence.model.product;

import com.example.demo.persistence.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "product_category")
@Data
@EqualsAndHashCode(callSuper =  true)
public class ProductCategory extends BaseEntity{

    private String name;
    private String code;

    @Column(name = "image_url")
    private String imageUrl; // store image path or URL

   
}