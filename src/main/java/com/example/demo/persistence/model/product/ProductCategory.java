package com.example.demo.persistence.model.product;

import java.util.List;

import com.example.demo.persistence.model.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_category") // use underscore for table name
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper =  true)
public class ProductCategory extends BaseEntity {

    private String name;
    private String code;

    @Column(name = "image_url")
    private String imageUrl; // store image path or URL

	/*
	 * @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval =
	 * true) private List<Product> products;
	 */
}
