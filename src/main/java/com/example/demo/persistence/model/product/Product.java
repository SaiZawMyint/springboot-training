package com.example.demo.persistence.model.product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.example.demo.persistence.model.item.Item;
import com.example.demo.persistence.model.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer status;
    private BigDecimal price;
	
	@ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;  // Many-to-One ProductCategory

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = true)
    private User createdBy;           // Many-to-One User

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;         // One-to-Many Items

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

}
