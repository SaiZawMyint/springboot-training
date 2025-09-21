package com.example.demo.dtos.item;

import java.math.BigDecimal;
import java.util.Date;

import com.example.demo.persistence.model.item.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

	private Long id;
	private String name;
	private BigDecimal sellPrice;
	private BigDecimal originalPrice;
	private Integer quantity; // e.g., 1, 2
	private Integer status; // 1 = Active, 2 = Inactive
	private String statusDesc; // "Active" or "Inactive"
	private Long productId;   
	private Date createdAt;
	private Long createdBy; // user ID who created
	private Date updatedAt;

	public ItemDTO(Item item) {
		if (item != null) {
			this.id = item.getId();
			this.name = item.getName();
			this.sellPrice = item.getSellPrice();
			this.originalPrice = item.getOriginalPrice();
			this.quantity = item.getQuantity();
			this.status = item.getStatus();
			this.productId = (item.getProduct() != null ? item.getProduct().getId() : null); 
			this.createdBy = item.getCreatedBy() != null ? item.getCreatedBy().getId() : null;
			this.createdAt = item.getCreatedAt();
			this.updatedAt = item.getUpdatedAt();

			this.statusDesc = status != null && status.equals(1) ? "Active" : "Inactive";

		}
	}

}
