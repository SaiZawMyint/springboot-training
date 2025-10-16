package com.example.demo.persistence.model.item;

import java.math.BigDecimal;

import com.example.demo.persistence.model.BaseEntity;
import com.example.demo.persistence.model.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper =  true)
public class Item extends BaseEntity {

    private String name;

    @Column(name = "sell_price")
    private BigDecimal sellPrice;

    @Column(name = "original_price")
    private BigDecimal originalPrice;

    private Integer quantity; // e.g., 1,2

    private Integer status;   // 1 = Active, 2 = Inactive

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

	/*
	 * @ManyToOne
	 *
	 * @JoinColumn(name = "created_by", nullable = false) private User createdBy;
	 */ // Many items created by one user

  /*@Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;*/

}
