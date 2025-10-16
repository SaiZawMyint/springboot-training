package com.example.demo.persistence.model.product;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.persistence.model.BaseEntity;
import com.example.demo.persistence.model.item.Item;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper =  true)
public class Product extends BaseEntity{
    private String name;
    private String description;
    private Integer status;
    private BigDecimal price;

	@ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
	private ProductCategory category;  // Many-to-One ProductCategory

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;         // One-to-Many Items

}
